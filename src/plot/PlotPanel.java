package plot;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import javax.swing.*;

public class PlotPanel extends JPanel {
    private final Plot plot;
    private static final int PADDING = 50;
    private static final int POINT_RADIUS = 3;

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

        drawAxes(g2);
        drawGrid(g2);
        drawCurves(g2);
    }

    private void drawAxes(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        
        g2.drawLine(PADDING, height-PADDING, width-PADDING, height-PADDING);
        g2.drawLine(PADDING, PADDING, PADDING, height-PADDING);
        
        g2.drawString(plot.getXAxis().getLabel(), width-PADDING-30, height-PADDING+20);
        g2.drawString(plot.getYAxis().getLabel(), PADDING+5, PADDING-10);
    }

    private void drawGrid(Graphics2D g2) {
        if (!plot.getGrid().isVisible()) return;
        
        int width = getWidth();
        int height = getHeight();
        Stroke oldStroke = g2.getStroke();
        Color oldColor = g2.getColor();
        
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, 
                                   BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0));
        g2.setColor(Color.LIGHT_GRAY);
        
        for (int x = PADDING; x <= width-PADDING; x += 50) {
            g2.drawLine(x, PADDING, x, height-PADDING);
        }
        
        for (int y = height-PADDING; y >= PADDING; y -= 50) {
            g2.drawLine(PADDING, y, width-PADDING, y);
        }
        
        g2.setStroke(oldStroke);
        g2.setColor(oldColor);
    }

    private void drawCurves(Graphics2D g2) {
        // Исправлено: использование generics для List
        for (Curve curve : plot.getCurves()) {
            Color color = Color.decode(curve.getColor());
            g2.setColor(color);
            
            List<Point2D.Double> points = curve.getPoints();
            Point2D prevPoint = null;
            
            for (Point2D.Double point : points) {
                Point2D screenPoint = scalePoint(point);
                
                g2.fillOval((int)screenPoint.getX()-POINT_RADIUS, 
                           (int)screenPoint.getY()-POINT_RADIUS, 
                           POINT_RADIUS*2, POINT_RADIUS*2);
                
                if (prevPoint != null) {
                    g2.draw(new Line2D.Double(prevPoint, screenPoint));
                }
                prevPoint = screenPoint;
            }
        }
    }

    private Point2D scalePoint(Point2D.Double point) {
        double x = PADDING + (point.x - plot.getXAxis().getMin()) * 
                  (getWidth() - 2*PADDING) / 
                  (plot.getXAxis().getMax() - plot.getXAxis().getMin());
        
        double y = getHeight() - PADDING - (point.y - plot.getYAxis().getMin()) * 
                  (getHeight() - 2*PADDING) / 
                  (plot.getYAxis().getMax() - plot.getYAxis().getMin());
        
        return new Point2D.Double(x, y);
    }
}