package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OrderDetail;
import com.example.demo.models.Orders;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrdersRepository;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<OrderDetail> getAllOrderDetails(String orderId){
		Orders o = ordersRepository.findById(orderId).get();
		
		List<OrderDetail> details = orderDetailRepository.findAllByOrders(o);
		return details;
	}
}

