package com.csidigital.management.service;

import com.csidigital.dao.entity.Order;
import com.csidigital.shared.dto.request.OrderRequest;
import com.csidigital.shared.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
   OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(OrderRequest orderRequest , Long id );

    void deleteOrder(Long id);
    List<Order> getProjectOrders();

}
