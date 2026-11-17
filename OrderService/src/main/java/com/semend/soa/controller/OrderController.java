package com.semend.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.semend.soa.entity.Order;
import com.semend.soa.service.OrderService;

@RestController
@RequestMapping
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Order addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}

	@GetMapping("/display")
	public List<Order> displayAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/display/{id}")
	public Order displayOrderById(@PathVariable int id) {
		return orderService.getOrderById(id);
	}
}
