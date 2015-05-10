package ua.yandex.pizza.repository;

import org.springframework.stereotype.Repository;
import ua.yandex.pizza.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Repository("orderRepository")
public class TestOrderRepositoryImpl implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public void saveOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Order getOrder(int index) {
        return null;
    }

    @Override
    public void updateOrder(int index) {

    }

    @Override
    public void deleteOrder(int index) {

    }
}
