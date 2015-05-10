package ua.yandex.pizza.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import ua.yandex.pizza.domain.Order;
import ua.yandex.pizza.repository.OrderRepository;

import java.util.List;
import ua.yandex.pizza.domain.Customer;
import ua.yandex.pizza.domain.Pizza;
import ua.yandex.pizza.repository.PizzaRepository;

public abstract class SimpleOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PizzaRepository pizzaRepository;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public abstract Order createNewOrder();
    
    @Override
    public Order createNewOrder(Customer customer, List<Integer> pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizza(id));            
        }
        Order order = createNewOrder();
        order.setPizzas(pizzas);
        order.setCustomer(customer);
        return order;
    }

    @Override
    public void placeOrder(Order newOrder) {
        orderRepository.saveOrder(newOrder);
    }

}
