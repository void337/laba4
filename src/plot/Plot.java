package plot;

import java.util.ArrayList;
import java.util.List;

public class Plot {
    private final String title;
    private final Axis xAxis;
    private final Axis yAxis;
    private final Grid grid;
    private final Legend legend;
    private final List<Curve> curves = new ArrayList<>();

    public Plot(String title, Axis xAxis, Axis yAxis, Grid grid, Legend legend) {
        this.title = title != null ? title : "Plot";
        this.xAxis = xAxis != null ? xAxis : new Axis("X", 0, 10, true);
        this.yAxis = yAxis != null ? yAxis : new Axis("Y", 0, 10, true);
        this.grid = grid != null ? grid : new Grid(true, "dotted", "#CCCCCC");
        this.legend = legend != null ? legend : new Legend(true, "top-right");
    }

    public void addCurve(Curve curve) {
        if (curve != null) curves.add(curve);
    }

    public void displayText() {
        System.out.println("=== " + title + " ===");
        System.out.println(xAxis);
        System.out.println(yAxis);
        System.out.println(grid);
        System.out.println(legend);
        System.out.println("Curves:");
        curves.forEach(System.out::println);
    }

    public void displayGraph() {
        PlotFrame frame = new PlotFrame(this);
        frame.setVisible(true);
    }

    public Axis getXAxis() { return xAxis; }
    public Axis getYAxis() { return yAxis; }
    public Grid getGrid() { return grid; }
    public Legend getLegend() { return legend; }
    public List<Curve> getCurves() { return curves; }
    public String getTitle() { return title; }
}