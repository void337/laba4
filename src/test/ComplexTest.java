package test;

import complex.Complex;
import complex.ComplexFunctions;

public class ComplexTest {
    public static void main(String[] args) {
        Complex z1 = new Complex(3, 4);
        Complex z2 = new Complex(1, -2);
        
        System.out.println("z1 = " + z1.toAlgebraicForm()); 
        System.out.println("z2 = " + z2.toAlgebraicForm());
        
        System.out.println("Sum: " + z1.add(z2));
        System.out.println("Product: " + z1.multiply(z2));
        System.out.println("Conjugate of z1: " + z1.conjugate());
        System.out.println("Trigonometric form of z1: " + z1.toTrigonometricForm());
        
        System.out.println("e^z1: " + ComplexFunctions.exp(z1));
        System.out.println("sin(z1): " + ComplexFunctions.sin(z1));
        System.out.println("cos(z1): " + ComplexFunctions.cos(z1));
    }
}