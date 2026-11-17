package com.semend.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semend.soa.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
