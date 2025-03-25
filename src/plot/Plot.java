package plot;

import java.util.ArrayList;
import java.util.List;

public class Plot {
    private final List<Curve> curves = new ArrayList<>();
    private final Axis xAxis;
    private final Axis yAxis;
    private final Grid grid;
    private final Legend legend;

    public Plot(Axis xAxis, Axis yAxis, Grid grid, Legend legend) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.grid = grid;
        this.legend = legend;
    }

    public void addCurve(Curve curve) {
        curves.add(curve);
    }

    public List<Curve> getCurves() { return curves; }  // Правильный возвращаемый тип
    public Axis getXAxis() { return xAxis; }
    public Axis getYAxis() { return yAxis; }
    public Grid getGrid() { return grid; }
    public Legend getLegend() { return legend; }

    public void display() {
        printTextInfo();
        new PlotFrame(this).setVisible(true);
    }

    private void printTextInfo() {
        System.out.println("=== Plot Information ===");
        System.out.println(xAxis);
        System.out.println(yAxis);
        System.out.println(grid);
        System.out.println(legend);
        System.out.println("Curves:");
        curves.forEach(System.out::println);
        System.out.println("=======================");
    }
}