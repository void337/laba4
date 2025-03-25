package plot;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import javax.swing.*;

public class PlotPanel extends JPanel {
    private final Plot plot;
    private static final int PADDING = 60;
    private static final int TICK_LENGTH = 5;
    private static final int POINT_RADIUS = 4;
    private static final Stroke AXIS_STROKE = new BasicStroke(2f);
    private static final Stroke GRID_STROKE = new BasicStroke(1f);
    private static final Stroke CURVE_STROKE = new BasicStroke(2.5f);
    private static final Font AXIS_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);

    public PlotPanel(Plot plot) {
        this.plot = plot;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                          RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawGrid(g2);
        drawAxes(g2);
        drawCurves(g2);
        drawLegend(g2);
    }

    private void drawAxes(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        
        g2.setStroke(AXIS_STROKE);
        g2.setColor(Color.BLACK);
        g2.setFont(AXIS_FONT);

        // Ось X
        g2.drawLine(PADDING, height-PADDING, width-PADDING, height-PADDING);
        // Ось Y
        g2.drawLine(PADDING, PADDING, PADDING, height-PADDING);

        // Подписи осей
        g2.setFont(LABEL_FONT);
        g2.drawString(plot.getXAxis().getLabel(), width-PADDING+10, height-PADDING+30);
        g2.drawString(plot.getYAxis().getLabel(), PADDING-50, PADDING-15);

        // Деления на оси X
        g2.setFont(AXIS_FONT);
        for (int i = 0; i <= 10; i++) {
            double value = plot.getXAxis().getMin() + i * 
                         (plot.getXAxis().getMax() - plot.getXAxis().getMin()) / 10;
            int x = PADDING + i * (width - 2*PADDING) / 10;
            g2.drawLine(x, height-PADDING-TICK_LENGTH, x, height-PADDING+TICK_LENGTH);
            String label = String.format("%.1f", value);
            g2.drawString(label, x-10, height-PADDING+20);
        }

        // Деления на оси Y
        for (int i = 0; i <= 10; i++) {
            double value = plot.getYAxis().getMin() + i * 
                         (plot.getYAxis().getMax() - plot.getYAxis().getMin()) / 10;
            int y = height - PADDING - i * (height - 2*PADDING) / 10;
            g2.drawLine(PADDING-TICK_LENGTH, y, PADDING+TICK_LENGTH, y);
            String label = String.format("%.1f", value);
            g2.drawString(label, PADDING-50, y+5);
        }
    }

    private void drawGrid(Graphics2D g2) {
        if (!plot.getGrid().isVisible()) return;
        
        int width = getWidth();
        int height = getHeight();
        
        Stroke oldStroke = g2.getStroke();
        Color oldColor = g2.getColor();
        
        try {
            g2.setColor(Color.decode(plot.getGrid().getColor()));
        } catch (Exception e) {
            g2.setColor(new Color(200, 200, 200));
        }

        switch (plot.getGrid().getStyle()) {
            case "dotted":
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, 
                    BasicStroke.JOIN_BEVEL, 0, new float[]{1, 3}, 0));
                break;
            case "dashed":
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, 
                    BasicStroke.JOIN_BEVEL, 0, new float[]{5, 5}, 0));
                break;
            default:
                g2.setStroke(GRID_STROKE);
        }

        // Вертикальные линии
        for (int i = 1; i < 10; i++) {
            int x = PADDING + i * (width - 2*PADDING) / 10;
            g2.drawLine(x, PADDING, x, height-PADDING);
        }

        // Горизонтальные линии
        for (int i = 1; i < 10; i++) {
            int y = height - PADDING - i * (height - 2*PADDING) / 10;
            g2.drawLine(PADDING, y, width-PADDING, y);
        }

        g2.setStroke(oldStroke);
        g2.setColor(oldColor);
    }

    private void drawCurves(Graphics2D g2) {
        g2.setStroke(CURVE_STROKE);
        
        for (Curve curve : plot.getCurves()) {
            Color curveColor;
            try {
                if (curve.getColor().startsWith("#")) {
                    curveColor = Color.decode(curve.getColor());
                } else {
                    switch (curve.getColor().toLowerCase()) {
                        case "black": curveColor = Color.BLACK; break;
                        case "white": curveColor = Color.WHITE; break;
                        case "red": curveColor = Color.RED; break;
                        case "green": curveColor = Color.GREEN; break;
                        case "blue": curveColor = Color.BLUE; break;
                        case "yellow": curveColor = Color.YELLOW; break;
                        case "cyan": curveColor = Color.CYAN; break;
                        case "magenta": curveColor = Color.MAGENTA; break;
                        case "orange": curveColor = Color.ORANGE; break;
                        case "pink": curveColor = Color.PINK; break;
                        case "gray": curveColor = Color.GRAY; break;
                        case "lightgray": curveColor = Color.LIGHT_GRAY; break;
                        case "darkgray": curveColor = Color.DARK_GRAY; break;
                        default: curveColor = Color.RED; // fallback
                    }
                }
            } catch (Exception e) {
                curveColor = Color.RED; // fallback
            }
            
            g2.setColor(curveColor);
            
            List<Point2D.Double> points = curve.getPoints();
            if (points.size() < 2) continue;
            
            Path2D path = new Path2D.Double();
            Point2D firstPoint = scalePoint(points.get(0));
            path.moveTo(firstPoint.getX(), firstPoint.getY());
            
            for (int i = 1; i < points.size(); i++) {
                Point2D point = scalePoint(points.get(i));
                path.lineTo(point.getX(), point.getY());
            }
            
            g2.draw(path);
            
            // Рисуем точки
            for (Point2D.Double point : points) {
                Point2D screenPoint = scalePoint(point);
                g2.fillOval((int)screenPoint.getX()-POINT_RADIUS/2, 
                           (int)screenPoint.getY()-POINT_RADIUS/2, 
                           POINT_RADIUS, POINT_RADIUS);
            }
        }
    }

    private void drawLegend(Graphics2D g2) {
        if (!plot.getLegend().isVisible()) return;
        
        int width = getWidth();
        int height = getHeight();
        int legendX, legendY;
        
        switch (plot.getLegend().getPosition()) {
            case "top-left":
                legendX = PADDING + 10;
                legendY = PADDING + 10;
                break;
            case "top-right":
                legendX = width - PADDING - 100;
                legendY = PADDING + 10;
                break;
            case "bottom-left":
                legendX = PADDING + 10;
                legendY = height - PADDING - 30 - 20 * plot.getCurves().size();
                break;
            case "bottom-right":
                legendX = width - PADDING - 100;
                legendY = height - PADDING - 30 - 20 * plot.getCurves().size();
                break;
            default:
                return;
        }
        
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRect(legendX, legendY, 90, 20 + 20 * plot.getCurves().size());
        g2.setColor(Color.BLACK);
        g2.drawRect(legendX, legendY, 90, 20 + 20 * plot.getCurves().size());
        
        g2.setFont(AXIS_FONT);
        g2.drawString("Легенда", legendX + 10, legendY + 15);
        
        int yOffset = 35;
        for (Curve curve : plot.getCurves()) {
            try {
                g2.setColor(Color.decode(curve.getColor()));
            } catch (Exception e) {
                g2.setColor(Color.RED);
            }
            g2.fillRect(legendX + 10, legendY + yOffset - 10, 15, 10);
            g2.setColor(Color.BLACK);
            g2.drawString(curve.getName(), legendX + 30, legendY + yOffset);
            yOffset += 20;
        }
    }

    private Point2D scalePoint(Point2D.Double point) {
        double xRange = plot.getXAxis().getMax() - plot.getXAxis().getMin();
        double yRange = plot.getYAxis().getMax() - plot.getYAxis().getMin();
        
        double x = PADDING + (point.x - plot.getXAxis().getMin()) * 
                  (getWidth() - 2*PADDING) / xRange;
        
        double y = getHeight() - PADDING - (point.y - plot.getYAxis().getMin()) * 
                  (getHeight() - 2*PADDING) / yRange;
        
        return new Point2D.Double(x, y);
    }
}