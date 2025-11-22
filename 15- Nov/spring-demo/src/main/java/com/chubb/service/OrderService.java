package com.chubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chubb.repository.OrderRepository;
import com.chubb.request.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // To implement Business Rules
    public Order insertOrder(Order order) {
        Order saved = orderRepository.save(order);
        log.debug(order.toString());
        System.out.println(order);
        return saved;
    }
}
