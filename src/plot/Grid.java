package plot;

public class Grid {
    private final boolean visible;
    private final String style;
    private final String color;

    public Grid(boolean visible, String style, String color) {
        this.visible = visible;
        this.style = style != null ? style : "solid";
        this.color = color != null ? color : "#CCCCCC";
    }

    public boolean isVisible() { return visible; }
    public String getStyle() { return style; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return String.format("Grid[visible=%b, style=%s, color=%s]", 
               visible, style, color);
    }
}