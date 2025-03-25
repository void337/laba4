package vehicles;

import engine.Engine;
import java.util.regex.Pattern;

public abstract class Car {
    protected String licensePlate;
    protected final String brand;
    protected final VehicleType type;
    protected String color;
    protected Engine engine;
    protected final int wheelCount;
    
    protected static final Pattern NORMAL_PLATE_PATTERN = 
        Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}([АВЕКМНОРСТУХ]{2})\\d{2,3}RUS$");

    public Car(String brand, VehicleType type, String color, Engine engine, int wheelCount) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.engine = engine;
        this.wheelCount = wheelCount;
    }

    public final String getBrand() {
        return brand;
    }

    public final VehicleType getType() {
        return type;
    }

    public final int getWheelCount() {
        return wheelCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean setLicensePlate(String licensePlate) {
        if (validateLicensePlate(licensePlate)) {
            this.licensePlate = licensePlate;
            return true;
        }
        return false;
    }

    protected abstract boolean validateLicensePlate(String licensePlate);

    @Override
    public String toString() {
        return String.format("Brand: %s, Type: %s, Color: %s, Engine: %s, Wheels: %d, Plate: %s",
                brand, type, color, engine, wheelCount, licensePlate != null ? licensePlate : "none");
    }
}