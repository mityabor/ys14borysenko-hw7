package ua.yandex.pizza.repository;

import ua.yandex.pizza.domain.Pizza;

import java.util.List;

public interface PizzaRepository {
    List<Pizza> getAllPizzas();
    void savePizza(Pizza pizza);
    Pizza getPizza(Integer id);
    void updatePizza(Integer id);
    void deletePizza(Integer id);

}
