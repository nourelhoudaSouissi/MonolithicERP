package com.csidigital.management.service;



import com.csidigital.shared.dto.request.PaymentTermRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;

import java.util.List;

public interface PaymentTermService {
    PaymentTermResponse createPaymentCondition(PaymentTermRequest request);
    List<PaymentTermResponse> getAllPaymentConditions();
    PaymentTermResponse getPaymentConditionById(Long id);

    PaymentTermResponse updatePaymentCondition(PaymentTermRequest request, Long id);

    void deletePaymentCondition(Long id);
}
