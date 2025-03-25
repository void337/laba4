package plot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Curve {
    private final String name;
    private final String color;
    private final String style;
    private final List<Point2D.Double> points;

    public Curve(String name, String color, String style, double[] xValues, double[] yValues) {
        if (xValues == null || yValues == null || xValues.length != yValues.length) {
            throw new IllegalArgumentException("Массивы координат должны быть одинаковой длины и не null");
        }
        
        this.name = name != null ? name : "Без названия";
        this.color = isValidColor(color) ? color : "#000000";
        this.style = isValidStyle(style) ? style : "solid";
        this.points = new ArrayList<>();
        
        for (int i = 0; i < xValues.length; i++) {
            points.add(new Point2D.Double(xValues[i], yValues[i]));
        }
    }

    private boolean isValidColor(String color) {
        if (color == null) return false;
        if (color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")) {
            return true;
        }
        
        switch (color.toLowerCase()) {
            case "black":
            case "white":
            case "red":
            case "green":
            case "blue":
            case "yellow":
            case "cyan":
            case "magenta":
            case "orange":
            case "pink":
            case "gray":
            case "lightgray":
            case "darkgray":
                return true;
            default:
                return false;
        }
    }

    private boolean isValidStyle(String style) {
        return style != null && style.matches("solid|dotted|dashed");
    }

    public List<Point2D.Double> getPoints() {
        return new ArrayList<>(points);
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public String getStyle() { return style; }

    @Override
    public String toString() {
        return String.format("Curve[name=%s, color=%s, style=%s, points=%d]", name, color, style, points.size());
    }
}