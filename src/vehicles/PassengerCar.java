package vehicles;
import engine.Engine;
public final class PassengerCar extends Car {
    public PassengerCar(String brand, String color, Engine engine) {
        super(brand, VehicleType.PASSENGER_CAR, color, engine, 4);
    }

    @Override
    protected boolean validateLicensePlate(String licensePlate) {
        return licensePlate == null || NORMAL_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}