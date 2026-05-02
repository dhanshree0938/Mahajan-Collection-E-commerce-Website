package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Cart;
import com.example.demo.models.OrderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrdersRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class OrderService {
   @Autowired
   private OrdersRepository ordersRepository;
   @Autowired
   private OrderDetailRepository orderDetailRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private CartRepository cartRepository;
   
   
   public void placeOrder(Orders orders) {
       int userId = orders.getUser().getId();
       
       User user = userRepository.findById(userId)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserId is invalid"));
       List<Cart> cartList = user.getCartList();
       
       int totalBill = cartList
                   .stream()
                   .mapToInt(Cart::getTotalPrice)
                   .sum();
       
       orders.setTotalBill(totalBill);
       
       ordersRepository.save(orders);
       
       List<OrderDetail> userOrderDetails = cartList
                               .stream()
                               .map(cart -> {
                                   OrderDetail o = new OrderDetail();
                                   o.setOrders(orders)    ;                                
                                   o.setProduct(cart.getProduct());                                    
                                   o.setQuantity(cart.getQuantity());                                
                                   return o;
                               })
                               .toList();
       
       orderDetailRepository.saveAll(userOrderDetails);
               
       cartRepository.deleteAll(cartList);
   }
}