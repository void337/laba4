package plot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Curve {
    private final String name;
    private final String color;
    private final String style;
    private final List<Point2D.Double> points;  // Явное указание типа

    public Curve(String name, String color, String style, double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues and yValues must have same length");
        }
        
        this.name = name;
        this.color = color;
        this.style = style;
        this.points = new ArrayList<>();
        
        for (int i = 0; i < xValues.length; i++) {
            points.add(new Point2D.Double(xValues[i], yValues[i]));
        }
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public String getStyle() { return style; }
    public List<Point2D.Double> getPoints() { return points; }  // Правильный возвращаемый тип

    @Override
    public String toString() {
        return String.format("Curve[name=%s, color=%s, style=%s, points=%d]", 
               name, color, style, points.size());
    }
}