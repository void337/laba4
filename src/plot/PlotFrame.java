package plot;

import javax.swing.*;

public class PlotFrame extends JFrame {
    public PlotFrame(Plot plot) {
        setTitle("Function Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        add(new PlotPanel(plot));
        setLocationRelativeTo(null);
    }
}