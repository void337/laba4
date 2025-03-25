package plot;

public class Axis {
    private final String label;
    private final double min;
    private final double max;
    private final boolean showGrid;

    public Axis(String label, double min, double max, boolean showGrid) {
        this.label = label != null ? label : "Axis";
        this.min = min;
        this.max = max;
        this.showGrid = showGrid;
    }

    public String getLabel() { return label; }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public boolean isShowGrid() { return showGrid; }

    @Override
    public String toString() {
        return String.format("Axis[label=%s, min=%.2f, max=%.2f, showGrid=%b]", label, min, max, showGrid);
    }
}