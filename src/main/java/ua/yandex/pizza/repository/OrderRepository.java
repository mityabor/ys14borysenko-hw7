package ua.yandex.pizza.repository;

import ua.yandex.pizza.domain.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrders();

    void saveOrder(Order order);
    Order getOrder(int index);
    void updateOrder(int index);
    void deleteOrder(int index);
}
