package ua.yandex.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.yandex.pizza.domain.Order;
import ua.yandex.pizza.domain.Pizza;
import ua.yandex.pizza.service.OrderService;
import ua.yandex.pizza.service.PizzaService;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        // Spring application context for repository beans
        ConfigurableApplicationContext repoAppContext
                = new ClassPathXmlApplicationContext(
                "springConfigRepository.xml");

        // Spring application context for service beans with access to repository beans context
        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                new String[]{"springConfigServices.xml"}, repoAppContext);

        PizzaService pizzaService = appContext.
                getBean("pizzaService", PizzaService.class);
        List<Pizza> pizzas = pizzaService.getAllPizzas();

        OrderService orderService = appContext.
                getBean("orderService", OrderService.class);

        Order newOrder1 = orderService.createNewOrder();
        newOrder1.addItems(pizzas.get(0), pizzas.get(1));
        orderService.placeOrder(newOrder1);

        Order newOrder2 = orderService.createNewOrder();
        newOrder2.addItems(pizzas.get(0));
        orderService.placeOrder(newOrder2);

        List<Order> orders = orderService.getAllOrders();

        orders.stream().forEach(System.out::println);

    }
}
