package plot;

public class FunctionFactory {
    public static Curve createFunction(String name, String color, int points, 
                                    double minX, double maxX, int functionType,
                                    double a, double b, double c) {
        if (points < 2) throw new IllegalArgumentException("Точек должно быть ≥ 2");
        if (minX >= maxX) throw new IllegalArgumentException("maxX должен быть > minX");

        double[] xValues = new double[points];
        double[] yValues = new double[points];

        for (int i = 0; i < points; i++) {
            double x = minX + (maxX - minX) * i / (points - 1);
            xValues[i] = x;
            
            switch (functionType) {
                case 1: // Линейная
                    yValues[i] = a * x + b;
                    break;
                case 2: // Квадратичная
                    yValues[i] = a * x * x + b * x + c;
                    break;
                case 3: // Синус
                    yValues[i] = a * Math.sin(b * x + c);
                    break;
                case 4: // Косинус
                    yValues[i] = a * Math.cos(b * x + c);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип функции");
            }
        }
        
        return new Curve(name, color, "solid", xValues, yValues);
    }
}