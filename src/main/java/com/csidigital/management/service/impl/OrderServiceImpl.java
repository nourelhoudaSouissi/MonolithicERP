package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Order;
import com.csidigital.dao.entity.OrderReferenceSequence;
import com.csidigital.dao.entity.Quotation;
import com.csidigital.dao.repository.OrderRepository;
import com.csidigital.dao.repository.OrderSequenceRepository;
import com.csidigital.dao.repository.QuotationRepository;
import com.csidigital.management.service.OrderService;
import com.csidigital.shared.dto.request.OrderRequest;
import com.csidigital.shared.dto.response.OrderResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository ;
    @Autowired
    private QuotationRepository quotationRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderSequenceRepository sequenceRepository;
    private String orderReference;
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        System.out.println(request);
        Quotation quotation = quotationRepository.findById(request.getQuotationNum())
                .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));;
        OrderReferenceSequence sequence = new OrderReferenceSequence();
        sequenceRepository.save(sequence);

        Order order = modelMapper.map(request, Order.class);
        orderReference = String.format("OD_%07d", sequence.getId());
        order.setRef(orderReference);
        order.setQuotation(quotation);
        order.setOrderDate(LocalDate.now());
        /*sequence.incrementNextValue();
        sequenceRepository.save(sequence);*/
        Order orderSaved = orderRepository.save(order);
        return modelMapper.map(orderSaved, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderList = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse response = modelMapper.map(order, OrderResponse.class);
            orderList.add(response);
        }

        return orderList;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order with id " +id+ " not found"));
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;
    }

    @Override
    public OrderResponse updateOrder(OrderRequest request , Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order with id: " + id + " not found"));
        modelMapper.map(request, existingOrder);
        Order savedOrder = orderRepository.save(existingOrder);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public void deleteOrder(Long id) {
     orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getProjectOrders() {
        return orderRepository.getProjectOrders();
    }
}
