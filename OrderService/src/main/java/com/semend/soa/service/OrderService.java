package com.semend.soa.service;

import java.util.List;

import com.semend.soa.entity.Order;

public interface OrderService {

	Order addOrder(Order order);

	List<Order> getAllOrders();

	Order getOrderById(int id);
}
