package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static List<Car> cars;

    static {
        cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cars.add(new Car("Brand-" + i, "Model-" + i, i));
        }
    }

    public static List<Car> getCars() {
        return cars;
    }
}
