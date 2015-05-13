package ua.yandex.pizza.service;

import ua.yandex.pizza.domain.Order;

import java.util.List;
import ua.yandex.pizza.domain.Customer;

public interface OrderService {
    List<Order> getAllOrders();

    Order createNewOrder();
    
    Order createNewOrder(Customer customer, List<Integer> pizzasID);
    void addItemToOrder(int orderID, int pizzaID);

    void placeOrder(Order newOrder);
}
