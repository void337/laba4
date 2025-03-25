package test;

import plot.*;

public class PlotDemo {
    public static void main(String[] args) {
        // 1. Создаем компоненты графика
        Axis xAxis = new Axis("X Axis", 0, 10, true);
        Axis yAxis = new Axis("Y Axis", -2, 2, true);
        Grid grid = new Grid(true, "dotted", "#CCCCCC");
        Legend legend = new Legend(true, "top-right");

        // 2. Создаем график
        Plot plot = new Plot(xAxis, yAxis, grid, legend);

        // 3. Генерируем данные
        int points = 20;
        double[] xValues = new double[points];
        double[] sinValues = new double[points];
        double[] cosValues = new double[points];

        for (int i = 0; i < points; i++) {
            xValues[i] = 10.0 * i / (points - 1);
            sinValues[i] = Math.sin(xValues[i]);
            cosValues[i] = Math.cos(xValues[i]);
        }

        // 4. Добавляем кривые
        plot.addCurve(new Curve("sin(x)", "#FF0000", "solid", xValues, sinValues));
        plot.addCurve(new Curve("cos(x)", "#0000FF", "solid", xValues, cosValues));

        // 5. Отображаем (вывод в консоль + графическое окно)
        plot.display();
    }
}