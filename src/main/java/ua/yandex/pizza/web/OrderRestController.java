package ua.yandex.pizza.web;

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

        Order order = orderService.getAllOrders().get(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "order",
            headers = "Content-Type=application/json")
    public ResponseEntity<Order> addPizzasToOrder(@RequestBody OrderDTO orderDTO) {
        Customer customer = orderDTO.getCustomer();
        List<Integer> pizzasID = orderDTO.getPizzasID();
        
        if ((pizzasID == null) || pizzasID.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderService.createNewOrder(customer, pizzasID);
        orderService.placeOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "item")
    public List<Pizza>  getItemsInOrder(@PathVariable("id") int id) {
        
        if (id < 0) {
            return null;
        }
        
        Order order = orderService.getAllOrders().get(id);
        
        return order.getPizzas();
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "order/{orderid}/item/{pizzaid}")
    public ResponseEntity<Pizza> deleteItemFromOrder(@PathVariable("orderid")int orderid, @PathVariable("pizzaid")int pizzaid)
    {
        if (orderid < 0 || pizzaid < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Order order = orderService.getAllOrders().get(orderid);
        
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Pizza pizza = order.getPizzas().get(pizzaid);
       
        if(pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(pizza,HttpStatus.OK);
    }
    
}
