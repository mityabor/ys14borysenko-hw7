package ua.yandex.pizza.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.yandex.pizza.domain.Order;
import ua.yandex.pizza.domain.Pizza;
import java.util.List;
import static org.junit.Assert.*;
import ua.yandex.pizza.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/springConfigServices.xml", "classpath:/springConfigRepository.xml"})
public class SimplePizzaServiceTest {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderService orderService;

    @Test
    public void serviceTest() {
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Order newOrder = orderService.createNewOrder();
        newOrder.setPizzas(pizzaList);
        newOrder.setCustomer(new Customer());
        orderService.placeOrder(newOrder);
        System.out.println(orderService.getAllOrders());
        assertNotNull(orderService.getAllOrders());
    }
}
