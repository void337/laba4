package test;

import java.util.Scanner;
import java.util.Set;
import plot.*;

public class PlotDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Построитель графиков функций ===");

        System.out.println("\nНастройка осей:");
        Axis xAxis = createAxis(scanner, "X");
        Axis yAxis = createAxis(scanner, "Y");
        
        System.out.println("\nНастройка сетки:");
        Grid grid = createGrid(scanner);
        
        System.out.println("\nНастройка легенды:");
        Legend legend = createLegend(scanner);
        
        Plot plot = new Plot("График функций", xAxis, yAxis, grid, legend);

        boolean addMore = true;
        while (addMore) {
            System.out.println("\nВыберите тип функции:");
            System.out.println("1. Линейная (y = kx + b)");
            System.out.println("2. Квадратичная (y = ax^2 + bx + c)");
            System.out.println("3. Синус (y = a*sin(bx + c))");
            System.out.println("4. Косинус (y = a*cos(bx + c))");
            
            int choice = getIntInput(scanner, "Ваш выбор (1-4): ", 1, 4);
            
            String name = getStringInput(scanner, "Введите название кривой: ");
            String color = getColorInput(scanner, "Введите цвет (например, #FF0000 или red): ");
            int points = getIntInput(scanner, "Введите количество точек (минимум 10): ", 10, 1000);
            
            double a = 0, b = 0, c = 0;
            switch (choice) {
                case 1:
                    a = getDoubleInput(scanner, "Введите коэффициент k: ");
                    b = getDoubleInput(scanner, "Введите коэффициент b: ");
                    break;
                case 2:
                    a = getDoubleInput(scanner, "Введите коэффициент a: ");
                    b = getDoubleInput(scanner, "Введите коэффициент b: ");
                    c = getDoubleInput(scanner, "Введите коэффициент c: ");
                    break;
                case 3:
                    a = getDoubleInput(scanner, "Введите амплитуду (a): ");
                    b = getDoubleInput(scanner, "Введите частоту (b): ");
                    c = getDoubleInput(scanner, "Введите фазу (c): ");
                    break;
                case 4:
                    a = getDoubleInput(scanner, "Введите амплитуду (a): ");
                    b = getDoubleInput(scanner, "Введите частоту (b): ");
                    c = getDoubleInput(scanner, "Введите фазу (c): ");
                    break;
            }
            
            Curve curve = FunctionFactory.createFunction(
                name, color, points, 
                xAxis.getMin(), xAxis.getMax(),
                choice, a, b, c
            );
            
            plot.addCurve(curve);
            addMore = getYesNoInput(scanner, "Добавить еще одну кривую? (y/n): ");
        }
        
        plot.displayText();
        plot.displayGraph();
        scanner.close();
    }

    private static Axis createAxis(Scanner scanner, String axisName) {
        System.out.println("\nНастройка оси " + axisName + ":");
        String label = getStringInput(scanner, "  Название оси: ");
        double min = getDoubleInput(scanner, "  Минимальное значение: ");
        double max = getDoubleInput(scanner, "  Максимальное значение: ");
        boolean showGrid = getYesNoInput(scanner, "  Показывать сетку? (y/n): ");
        return new Axis(label, min, max, showGrid);
    }

    private static Grid createGrid(Scanner scanner) {
        boolean visible = getYesNoInput(scanner, "  Показывать сетку? (y/n): ");
        if (!visible) return new Grid(false, "", "");
        
        String style = getStringInput(scanner, "  Стиль (solid/dotted/dashed): ");
        String color = getColorInput(scanner, "  Цвет (например, #CCCCCC): ");
        return new Grid(true, style, color);
    }

    private static Legend createLegend(Scanner scanner) {
        boolean visible = getYesNoInput(scanner, "  Показывать легенду? (y/n): ");
        if (!visible) {
            return new Legend(false, "");
        }
        
        while (true) {
            String position = getStringInput(scanner, 
                "  Позиция (top-left/top-right/bottom-left/bottom-right): ");
            
            if (isValidPosition(position)) {
                return new Legend(true, position);
            }
            
            System.out.println("  Ошибка: Недопустимая позиция. Допустимые значения: " + "top-left, top-right, bottom-left, bottom-right");
        }
    }
    
private static boolean isValidPosition(String position) {
    Set<String> validPositions = Set.of(
        "top-left", "top-right", "bottom-left", "bottom-right"
    );
    return position != null && validPositions.contains(position.toLowerCase());
}
    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String getColorInput(Scanner scanner, String prompt) {
        while (true) {
            String input = getStringInput(scanner, prompt);
            if (input.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$|^[a-zA-Z]+$")) {
                return input;
            }
            System.out.println("Некорректный цвет. Используйте формат #RRGGBB или название цвета.");
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }
            scanner.nextLine();
            System.out.println("Некорректный ввод. Пожалуйста, введите число.");
        }
    }

    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    return value;
                }
            }
            scanner.nextLine();
            System.out.printf("Пожалуйста, введите целое число от %d до %d.\n", min, max);
        }
    }

    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Пожалуйста, введите 'y' (да) или 'n' (нет).");
        }
    }
}