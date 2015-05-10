package ua.yandex.pizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yandex.pizza.domain.Pizza;
import ua.yandex.pizza.repository.PizzaRepository;

import java.util.List;

@Service("pizzaService")
public class SimplePizzaService implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public void setPizzaRepository(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Pizza getPizzaById(int id) {        
        return pizzaRepository.getPizza(id);
    }

    @Override
    public void addPizza(Pizza pizza) {
        pizzaRepository.savePizza(pizza);
    }

}
