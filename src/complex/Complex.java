package complex;

public class Complex {
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(double real) {
        this(real, 0);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new Complex(newReal, newImaginary);
    }

    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    public double magnitude() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public double argument() {
        return Math.atan2(imaginary, real);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Complex)) return false;
        Complex other = (Complex) obj;
        return Double.compare(real, other.real) == 0 && 
               Double.compare(imaginary, other.imaginary) == 0;
    }

    public String toAlgebraicForm() {
        return String.format("%.2f %s %.2fi", 
            real, 
            imaginary >= 0 ? "+" : "-", 
            Math.abs(imaginary));
    }

    public String toTrigonometricForm() {
        return String.format("%.2f*(cos(%.2f) + i*sin(%.2f))", 
            magnitude(), 
            argument(), 
            argument());
    }

    @Override
    public String toString() {
        return toAlgebraicForm();
    }
}