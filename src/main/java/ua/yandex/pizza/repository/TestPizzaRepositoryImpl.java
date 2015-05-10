package ua.yandex.pizza.repository;

import ua.yandex.pizza.domain.Pizza;

import java.util.List;

public class TestPizzaRepositoryImpl implements PizzaRepository {

    private List<Pizza> pizzas;

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    @Override
    public void savePizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    @Override
    public Pizza getPizza(Integer id) {
        for (Pizza pizza : pizzas) {
            if (pizza.getId().equals(id)) {
                return pizza;
            }
        }
        return null;
    }

    @Override
    public void updatePizza(Integer id) {

    }

    @Override
    public void deletePizza(Integer id) {
    }
}
