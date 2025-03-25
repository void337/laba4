package test;

import vehicles.*;
import engine.Engine;

public class CarTest {
    public static void main(String[] args) {
        Engine engine = new Engine("SN12345", 150, 2.0, "Gasoline", 4, 8.5);
        
        Car passengerCar = new PassengerCar("Toyota", "Red", engine);
        System.out.println("Passenger Car: " + passengerCar);
        
        boolean plateSet = passengerCar.setLicensePlate("А123ВС456RUS");
        System.out.println("License plate set: " + plateSet);
        System.out.println("After plate set: " + passengerCar);
        
        Engine newEngine = new Engine("SN67890", 200, 2.5, "Gasoline", 6, 9.0);
        passengerCar.setEngine(newEngine);
        passengerCar.setColor("Blue");
        System.out.println("After changes: " + passengerCar);
    }
}