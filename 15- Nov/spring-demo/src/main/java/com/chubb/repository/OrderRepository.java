package com.chubb.repository;

import org.springframework.data.repository.CrudRepository;

import com.chubb.request.Order;

public interface OrderRepository extends CrudRepository<Order , Integer> {



}
