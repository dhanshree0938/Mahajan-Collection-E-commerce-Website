package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.OrderDetail;
import com.example.demo.models.Orders;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{	
	List<OrderDetail> findAllByOrders(Orders orders);
}



