package complex;

public class ComplexFunctions {
    private ComplexFunctions() {} // Prevent instantiation

    public static Complex exp(Complex z) {
        double expReal = Math.exp(z.getReal());
        return new Complex(
            expReal * Math.cos(z.getImaginary()),
            expReal * Math.sin(z.getImaginary())
        );
    }

    public static Complex sin(Complex z) {
        Complex iz = new Complex(-z.getImaginary(), z.getReal());
        Complex eiz = exp(iz);
        Complex eMinusIz = exp(iz.multiply(new Complex(-1)));
        return eiz.subtract(eMinusIz).divide(new Complex(0, 2));
    }

    public static Complex cos(Complex z) {
        Complex iz = new Complex(-z.getImaginary(), z.getReal());
        Complex eiz = exp(iz);
        Complex eMinusIz = exp(iz.multiply(new Complex(-1)));
        return eiz.add(eMinusIz).divide(new Complex(2));
    }

    public static Complex tan(Complex z) {
        return sin(z).divide(cos(z));
    }

    public static Complex cot(Complex z) {
        return cos(z).divide(sin(z));
    }

    public static Complex sinh(Complex z) {
        Complex ez = exp(z);
        Complex eMinusZ = exp(z.multiply(new Complex(-1)));
        return ez.subtract(eMinusZ).divide(new Complex(2));
    }

    public static Complex cosh(Complex z) {
        Complex ez = exp(z);
        Complex eMinusZ = exp(z.multiply(new Complex(-1)));
        return ez.add(eMinusZ).divide(new Complex(2));
    }

    public static Complex tanh(Complex z) {
        return sinh(z).divide(cosh(z));
    }

    public static Complex coth(Complex z) {
        return cosh(z).divide(sinh(z));
    }
}