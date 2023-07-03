package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import web.service.Service;

import java.util.List;

@Controller
public class CarController {
    @GetMapping(value = "/cars")
    public String printCars(@RequestParam(name = "count", required = false) String count, ModelMap model) {
        Service service = new CarService();
        List<Car> cars;
        if (count == null) cars = service.getCars();
        else cars = service.getCars(Integer.parseInt(count));
        model.addAttribute("cars", cars);
        return "cars";
    }
}
