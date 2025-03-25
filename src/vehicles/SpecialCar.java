package vehicles;
import engine.Engine;
import java.util.regex.Pattern;

public class SpecialCar extends Car {
    private static final Pattern SPECIAL_PLATE_PATTERN = 
        Pattern.compile("^[АВЕКМНОРСТУХ]{2}\\d{3}RUS$");

    public SpecialCar(String brand, String color, Engine engine) {
        super(brand, VehicleType.SPECIAL, color, engine, 4);
    }

    @Override
    protected boolean validateLicensePlate(String licensePlate) {
        return licensePlate == null || SPECIAL_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}