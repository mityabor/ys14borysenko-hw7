package ua.yandex.pizza.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component("orderSample")
@Scope("prototype")
public class Order {
    private Integer id;
    private Date date;
    private String name;
    private Customer customer;
    private List<Pizza> pizzas = new ArrayList<>();
    private Double price;
    private static int numberOfOrders = 0;

    public Order() {
        date = new Date();
        name = date.getTime() + " / " + numberOfOrders;
        increaseNumberOfOrders();
    }

    private static void increaseNumberOfOrders() {
        numberOfOrders++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addItems(Pizza... pizza) {
        Collections.addAll(pizzas, pizza);
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Double getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }    
    
    @Override
    public String toString() {
        return date.toString() + " " + id + ": " + pizzas.toString();
    }

}
