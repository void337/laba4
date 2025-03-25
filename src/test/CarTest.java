package test;

import engine.Engine;
import java.util.Scanner;
import vehicles.*;

public class CarTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nВведите параметры двигателя:");
        Engine engine = createEngine(scanner);
        
        System.out.println("\nВведите параметры автомобиля:");
        Car car = createCar(scanner, engine);
        
        System.out.println("\nСоздан автомобиль: " + car);
        
        System.out.println("\nВведите номерной знак (формат А123ВС456RUS):");
        String plate = getValidLicensePlate(scanner);
        boolean plateSet = car.setLicensePlate(plate);
        System.out.println("Номерной знак " + (plateSet ? "успешно установлен" : "некорректен"));
        System.out.println("Текущие данные: " + car);
        
        System.out.println("\nМодификация автомобиля:");
        System.out.println("Введите новые параметры двигателя:");
        Engine newEngine = createEngine(scanner);
        car.setEngine(newEngine);
        
        System.out.println("Введите новый цвет автомобиля:");
        String newColor = getNonEmptyInput(scanner, "Цвет: ");
        car.setColor(newColor);
        
        System.out.println("\nИтоговые данные автомобиля:");
        System.out.println(car);
        
        scanner.close();
    }
    
    private static Engine createEngine(Scanner scanner) {
        String serialNumber = getNonEmptyInput(scanner, "Серийный номер: ");
        int power = getIntInput(scanner, "Мощность (л.с.): ", 50, 1000);
        double volume = getDoubleInput(scanner, "Объем (л): ", 0.5, 10.0);
        String fuelType = getNonEmptyInput(scanner, "Тип топлива: ");
        int cylinders = getIntInput(scanner, "Количество цилиндров: ", 1, 16);
        double consumption = getDoubleInput(scanner, "Расход топлива (л/100км): ", 2.0, 30.0);
        
        return new Engine(serialNumber, power, volume, fuelType, cylinders, consumption);
    }
    
    private static Car createCar(Scanner scanner, Engine engine) {
        String brand = getNonEmptyInput(scanner, "Марка: ");
        String color = getNonEmptyInput(scanner, "Цвет: ");
        
        return new PassengerCar(brand, color, engine);
    }
    
    private static String getValidLicensePlate(Scanner scanner) {
        final String PATTERN = "^[АВЕКМНОРСТУХABEKMHOPCTYX]\\d{3}[АВЕКМНОРСТУХABEKMHOPCTYX]{2}\\d{2,3}RUS$";
        
        while (true) {
            System.out.print("Номерной знак: ");
            String plate = scanner.nextLine().trim().toUpperCase();
            
            if (plate.matches(PATTERN)) {
                return plate;
            }
        }
    }
    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не может быть пустым!");
        }
    }
    
    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    scanner.nextLine();
                    return value;
                }
            }
            scanner.nextLine();
            System.out.printf("Введите целое число от %d до %d!\n", min, max);
        }
    }
    
    private static double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    scanner.nextLine();
                    return value;
                }
            }
            scanner.nextLine();
            System.out.printf("Введите число от %.1f до %.1f!\n", min, max);
        }
    }
}