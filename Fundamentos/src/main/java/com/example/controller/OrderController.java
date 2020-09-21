package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> postOrder(@Valid @RequestBody Order order, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Order orderDB = orderService.createOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id")Long id){
        Order orderDB = orderService.getOrderById(id);
        if (orderDB == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orderDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable("id") Long id){
        Order orderDB = orderService.getOrderById(id);
        if (orderDB == null) {
            return ResponseEntity.notFound().build();
        }
        orderDB = orderService.updateOrderStatus(id);

        return ResponseEntity.ok(orderDB);
    }

    @GetMapping("/restaurants/{restaurantId}/status/{statusId}")
    public ResponseEntity<List<Order>> listordersbyrestaurantandstatus(@PathVariable("restaurantId") Long restaurantId,
                                                                       @PathVariable("statusId") Long statusId){
        List<Order> orders = orderService.getOrdersByRestaurantAndStatus(restaurantId, statusId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<List<Order>> listOrdersByRestaurant(@PathVariable("id") Long id){
        List<Order> orders = orderService.getOrdersByRestaurant(id);
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }
}
