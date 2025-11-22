package com.chubb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.request.Address;
import com.chubb.request.Order;
import com.chubb.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/Order")
    String getOrder() {
        return "hello";
    }

    @PostMapping("/totalPrice")
    float getTotalPrice(@RequestBody @Valid Order order) {
        return order.getPrice() * order.getQuantity();
    }

    @PostMapping("/address")
    Address getAddress(@RequestBody @Valid Order order) {
        return order.getAddress();
    }

    @PostMapping("/order")
    Order saveOrder(@RequestBody @Valid Order order) {
        log.debug("logger added");
        return service.insertOrder(order);
    }
}
