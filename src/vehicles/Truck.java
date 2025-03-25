package vehicles;
import engine.Engine;
public final class Truck extends Car {
    public Truck(String brand, String color, Engine engine) {
        super(brand, VehicleType.TRUCK, color, engine, 6);
    }

    @Override
    protected boolean validateLicensePlate(String licensePlate) {
        return licensePlate == null || NORMAL_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}