package plot;

public class Legend {
    private final boolean visible;
    private final String position;

    public Legend(boolean visible, String position) {
        this.visible = visible;
        this.position = position != null ? position : "top-right";
    }

    public boolean isVisible() { return visible; }
    public String getPosition() { return position; }

    @Override
    public String toString() {
        return String.format("Legend[visible=%b, position=%s]", 
               visible, position);
    }
}