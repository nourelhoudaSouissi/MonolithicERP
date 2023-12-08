package com.csidigital.management.service.impl;



import com.csidigital.dao.entity.PaymentTerm;
import com.csidigital.dao.repository.PaymentTermRepository;
import com.csidigital.management.service.PaymentTermService;
import com.csidigital.shared.dto.request.PaymentTermRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PaymentTermServiceImpl implements PaymentTermService {
    @Autowired
    private PaymentTermRepository paymentConditionRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PaymentTermResponse createPaymentCondition(PaymentTermRequest request) {
        PaymentTerm paymentCondition = modelMapper.map(request, PaymentTerm.class);
        PaymentTerm savedPaymentCondition= paymentConditionRepository.save(paymentCondition);
        return modelMapper.map(savedPaymentCondition, PaymentTermResponse.class);
    }

    @Override
    public List<PaymentTermResponse> getAllPaymentConditions() {
        List<PaymentTerm> paymentConditions = paymentConditionRepository.findAll();
        List<PaymentTermResponse> paymentConditionResponses = new ArrayList<>();


        for (PaymentTerm paymentCondition: paymentConditions) {
            PaymentTermResponse response = modelMapper.map(paymentCondition, PaymentTermResponse.class);
            paymentConditionResponses.add(response);
        }

        return paymentConditionResponses;
    }

    @Override
    public PaymentTermResponse getPaymentConditionById(Long id) {
        PaymentTerm paymentCondition = paymentConditionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("PaymentCondition with id " +id+ " not found"));
        PaymentTermResponse paymentConditionResponse = modelMapper.map(paymentCondition, PaymentTermResponse.class);
        return paymentConditionResponse;
    }

    @Override
    public PaymentTermResponse updatePaymentCondition(PaymentTermRequest request, Long id) {
        PaymentTerm existingPaymentCondition = paymentConditionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("PaymentCondition with id: " + id + " not found"));
        modelMapper.map(request, existingPaymentCondition);
        PaymentTerm savedPaymentCondition = paymentConditionRepository.save(existingPaymentCondition);
        return modelMapper.map(savedPaymentCondition, PaymentTermResponse.class);
    }

    @Override
    public void deletePaymentCondition(Long id) {
        paymentConditionRepository.deleteById(id);
    }
}
