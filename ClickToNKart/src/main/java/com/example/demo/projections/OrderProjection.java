package com.example.demo.projections;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Orders;

@Projection(types = {Orders.class})
public interface OrderProjection {
	
	public String getOrderId();
	public String getPaymentId();
	public LocalDateTime getOrderDate() ;
	public int getTotalBill();	
}


