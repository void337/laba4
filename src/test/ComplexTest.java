package test;

import complex.Complex;
import complex.ComplexFunctions;
import java.util.Scanner;

public class ComplexTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Введите первое комплексное число:");
        Complex z1 = readComplexNumber(scanner);
        
        System.out.println("Введите второе комплексное число:");
        Complex z2 = readComplexNumber(scanner);
        
        System.out.println("\nРезультаты:");
        System.out.println("z1 = " + z1.toAlgebraicForm());
        System.out.println("z2 = " + z2.toAlgebraicForm());
        
        System.out.println("Сумма: " + z1.add(z2));
        System.out.println("Произведение: " + z1.multiply(z2));
        System.out.println("Сопряженное z1: " + z1.conjugate());
        System.out.println("Тригонометрическая форма z1: " + z1.toTrigonometricForm());
        
        System.out.println("e^z1: " + ComplexFunctions.exp(z1));
        System.out.println("sin(z1): " + ComplexFunctions.sin(z1));
        System.out.println("cos(z1): " + ComplexFunctions.cos(z1));
        
        scanner.close();
    }
    
    private static Complex readComplexNumber(Scanner scanner) {
        double real = readDouble(scanner, "  Действительная часть: ");
        double imaginary = readDouble(scanner, "  Мнимая часть: ");
        return new Complex(real, imaginary);
    }
    
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Ошибка: введите число!");
                scanner.nextLine();
            }
        }
    }
}