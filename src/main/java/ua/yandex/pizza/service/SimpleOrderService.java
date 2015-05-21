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
    public int getNumberOfOrders() {
        return orderRepository.getAllOrders().size();
    }
    
    @Override
    public Order getOrder(int orderID) {
        return orderRepository.getAllOrders().get(orderID);
    }
    
    @Override
    public abstract Order createNewOrder();
    
    @Override
    public Order createNewOrder(Customer customer, List<Integer> pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
        double price = 0;
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizza(id));
            price += pizzaRepository.getPizza(id).getPrice();
        }
        Order order = createNewOrder();
        order.setPizzas(pizzas);
        order.setCustomer(customer);
        order.setPrice(price);
        return order;
    }

    
    @Override
    public void placeOrder(Order newOrder) {
        orderRepository.saveOrder(newOrder);
    }
    
    @Override
    public void addItemToOrder(int orderId, int pizzaId) {
        Order order = orderRepository.getOrder(orderId);
        Pizza pizza = pizzaRepository.getPizza(pizzaId);
        order.getPizzas().add(pizza);
        order.setPrice(order.getPrice()+pizza.getPrice());
    }
    
    @Override
    public void removeItemFromOrder(int orderId, int pizzaId) {
        Order order = orderRepository.getOrder(orderId);
        Pizza pizza = pizzaRepository.getPizza(pizzaId);
        order.getPizzas().remove(pizza);
        order.setPrice(order.getPrice()-pizza.getPrice());
    
    }
    
    

}
