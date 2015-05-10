package ua.yandex.pizza.service;

import ua.yandex.pizza.domain.Pizza;

import java.util.List;


public interface PizzaService {
    List<Pizza> getAllPizzas();

    Pizza getPizzaById(int id);

    void addPizza(Pizza pizza);
}
