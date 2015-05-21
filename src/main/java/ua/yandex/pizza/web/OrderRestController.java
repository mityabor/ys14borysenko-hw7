package ua.yandex.pizza.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.yandex.pizza.domain.Customer;
import ua.yandex.pizza.domain.Order;
import ua.yandex.pizza.domain.Pizza;
import ua.yandex.pizza.service.OrderService;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        if (id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order = orderService.getOrder(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "order/{orderid}/item/")
    public List<Pizza> getItemsInOrder(@PathVariable("id") int id) {

        if (id < 0) {
            return null;
        }

        Order order = orderService.getOrder(id);

        return order.getPizzas();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "order/{orderid}/item/{pizzaid}")
    public ResponseEntity<Pizza> deleteItemFromOrder(@PathVariable("orderid") int orderid, @PathVariable("pizzaid") int pizzaid) {
        if (orderid < 0 || pizzaid < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order = orderService.getOrder(orderid);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        
        Pizza pizza = order.getPizzas().get(pizzaid);
        orderService.removeItemFromOrder(orderid, pizzaid);
        if (pizza == null || !order.getPizzas().contains(pizza)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    // POST http://localhost:8080/PizzaDelivery/pages/order {"customer":{"name":"Andrii","phone":"1234567"},"pizzasID":[1,3,2]}
    @RequestMapping(
            method = RequestMethod.POST,
            value = "order",
            headers = "Content-Type=application/json")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDto) {
        System.out.println(orderDto);
        
        Order order = orderService.createNewOrder(orderDto.getCustomer(), 
                orderDto.getPizzasID());
        order.setDate(new Date());
        order.setId(orderService.getNumberOfOrders());
        order.setName(orderDto.getCustomer()+ orderDto.getPizzasID().toString());
        System.out.println(order);
        orderService.placeOrder(order);
        
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "order/{orderid}/item/{pizzaid}")
    public ResponseEntity<Order> addItemToOrder(@PathVariable("orderid") int orderid, @PathVariable("pizzaid") int pizzaid) {
        if (orderid < 0 || pizzaid < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        orderService.addItemToOrder(orderid, pizzaid);

        Order order = orderService.getOrder(orderid);
        

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "order/{orderid}/customer")
    public ResponseEntity<Order> updateCustomerInOrder(@PathVariable("orderid") int orderid,
            @RequestBody Customer customer) {
        if (orderid < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order = orderService.getOrder(orderid);
        

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        order.setCustomer(customer);

   

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
