package ua.yandex.pizza.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.yandex.pizza.domain.Pizza;
import ua.yandex.pizza.service.PizzaService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    // GET http://localhost:8080/PizzaDelivery/pages/hello
    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello from RESTController", HttpStatus.OK);
        
    }

    // GET http://localhost:8080/PizzaDelivery/pages/pizza
    @RequestMapping(method = RequestMethod.GET, value = "pizza")
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return new ResponseEntity<>(pizzaService.getAllPizzas(), HttpStatus.OK);
    }

    // GET http://localhost:8080/PizzaDelivery/pages/pizza/1
    @RequestMapping(method = RequestMethod.GET, value = "pizza/{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable("id") int id) {
        if (id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    // POST http://localhost:8080/PizzaDelivery/pages/pizza {"id":2,"name":"SplendidPizza","price":100.0,"type":"Ohotnichya"}
    @RequestMapping(
            method = RequestMethod.POST,
            value = "pizza",
            headers = "Content-Type=application/json")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
        System.out.println(pizza);

        HttpStatus status;
        if (pizzaService.getAllPizzas().contains(pizza)) {
            status = HttpStatus.CONFLICT;
        } else {
            pizzaService.addPizza(pizza);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<>(pizza, status);
    }
}
