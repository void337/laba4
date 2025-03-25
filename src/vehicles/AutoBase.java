package vehicles;

import java.util.ArrayList;
import java.util.List;

public class AutoBase {
    private final List<Car> cars;
    private final int maxCapacity;
    
    // Для хранения состояний автомобилей
    private final List<Car> carsOnBase = new ArrayList<>();
    private final List<Car> carsOnRoute = new ArrayList<>();
    private final List<Car> carsInRepair = new ArrayList<>();

    public AutoBase(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be positive");
        }
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public boolean addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (cars.size() >= maxCapacity) {
            return false;
        }
        cars.add(car);
        carsOnBase.add(car); // Новый автомобиль по умолчанию на базе
        return true;
    }

    public boolean removeCar(Car car) {
        if (car == null) {
            return false;
        }
        // Удаляем автомобиль из всех списков состояний
        carsOnBase.remove(car);
        carsOnRoute.remove(car);
        carsInRepair.remove(car);
        return cars.remove(car);
    }

    public boolean sendToRoute(Car car) {
        if (car == null || !cars.contains(car)) {
            return false;
        }
        if (carsOnBase.remove(car)) {
            carsOnRoute.add(car);
            return true;
        }
        return false;
    }

    public boolean sendToRepair(Car car) {
        if (car == null || !cars.contains(car)) {
            return false;
        }
        if (carsOnBase.remove(car) || carsOnRoute.remove(car)) {
            carsInRepair.add(car);
            return true;
        }
        return false;
    }

    public boolean returnToBase(Car car) {
        if (car == null || !cars.contains(car)) {
            return false;
        }
        if (carsOnRoute.remove(car) || carsInRepair.remove(car)) {
            carsOnBase.add(car);
            return true;
        }
        return false;
    }

    public List<Car> getAvailableCars() {
        return new ArrayList<>(carsOnBase); // Возвращаем копию, чтобы избежать модификации
    }

    public List<Car> getCarsInRepair() {
        return new ArrayList<>(carsInRepair);
    }

    public List<Car> getCarsOnRoute() {
        return new ArrayList<>(carsOnRoute);
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    public int getAvailableSpots() {
        return maxCapacity - cars.size();
    }
}