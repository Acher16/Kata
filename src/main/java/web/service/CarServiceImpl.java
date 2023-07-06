package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    private List<Car> cars;

    {
        cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cars.add(new Car("Brand-" + i, "Model-" + i, i));
        }
    }

    @Override
    public List<Car> getCars(Integer count) {
        if (count != null) {
            if (count < cars.size()) return cars.subList(0, count);
        }
        return cars;
    }
}
