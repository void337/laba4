package test;

import engine.Engine;
import vehicles.*;

public class AutoBaseTest {
    public static void main(String[] args) {
        AutoBase base = new AutoBase(10);
        
        Engine engine1 = new Engine("SN111", 120, 1.8, "Gasoline", 4, 7.5);
        Engine engine2 = new Engine("SN222", 250, 3.0, "Diesel", 6, 10.0);
        
        Car car1 = new PassengerCar("Toyota", "Red", engine1);
        Car car2 = new Truck("Volvo", "Blue", engine2);
        
        base.addCar(car1);
        base.addCar(car2);
        
        System.out.println("Доступные машины:");
        for (Car car : base.getAvailableCars()) {
            System.out.println(car);
        }
    }
}