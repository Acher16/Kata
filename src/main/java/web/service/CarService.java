package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService implements Service {
    private static List<Car> cars;

    static {
        cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cars.add(new Car("Brand-" + i, "Model-" + i, i));
        }
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public List<Car> getCars(int count) {
        if (count < cars.size()) return cars.subList(0, count);
        return cars;
    }
}
