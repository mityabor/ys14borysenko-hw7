package ua.yandex.pizza.web;

import java.util.List;
import ua.yandex.pizza.domain.Customer;

public class OrderDTO {
    private Customer customer;
    private List<Integer> pizzasID;

    public OrderDTO() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Integer> getPizzasID() {
        return pizzasID;
    }

    public void setPizzasID(List<Integer> pizzasID) {
        this.pizzasID = pizzasID;
    }
    
    
    
}
