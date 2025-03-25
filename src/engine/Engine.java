package engine;

public class Engine {
    private final String serialNumber;
    private final double power;
    private final double volume;
    private final String fuelType;
    private final int cylinders;
    private final double fuelConsumption;

    public Engine(String serialNumber, double power, double volume, 
                 String fuelType, int cylinders, double fuelConsumption) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.volume = volume;
        this.fuelType = fuelType;
        this.cylinders = cylinders;
        this.fuelConsumption = fuelConsumption;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPower() {
        return power;
    }

    public double getVolume() {
        return volume;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getCylinders() {
        return cylinders;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("Engine[SN: %s, Power: %.1f hp, Volume: %.1fL, Fuel: %s, Cylinders: %d, Consumption: %.1f L/100km]",
                serialNumber, power, volume, fuelType, cylinders, fuelConsumption);
    }
}