package vehicles;
import engine.Engine;
public final class Bus extends Car {
    public Bus(String brand, String color, Engine engine) {
        super(brand, VehicleType.BUS, color, engine, 6);
    }

    @Override
    protected boolean validateLicensePlate(String licensePlate) {
        return licensePlate == null || NORMAL_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}