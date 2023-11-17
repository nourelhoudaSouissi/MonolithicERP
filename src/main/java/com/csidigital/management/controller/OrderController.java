package com.csidigital.management.controller;

import com.csidigital.dao.entity.Employee;
import com.csidigital.dao.entity.Order;
import com.csidigital.management.service.impl.OrderServiceImpl;
import com.csidigital.shared.dto.request.OrderRequest;
import com.csidigital.shared.dto.response.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/orders")
//@CrossOrigin(origins = "${cross.origin.url}")
public class OrderController {
    @Autowired

    private OrderServiceImpl OrderService ;

    @GetMapping()
    public List<OrderResponse> getAllOrders() {
        return OrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        return OrderService.getOrderById(id);
    }

    @PostMapping()
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return OrderService.createOrder(orderRequest);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrder(@Valid @RequestBody OrderRequest orderRequest,
                                                 @PathVariable Long id){
        return OrderService.updateOrder(orderRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        OrderService.deleteOrder(id);
    }


    @GetMapping("/getProjectOrders")
    public List<Order>  getProjectOrders(){

        return OrderService.getProjectOrders();
    }
}
