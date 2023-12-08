package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.Currency;
import com.csidigital.shared.enumeration.PaymentCondition;
import com.csidigital.shared.enumeration.PaymentMode;
import lombok.Data;

@Data
public class PartnerFinancialRequest {
    private Long id;
    private Currency currency;
    private PaymentMode paymentMode;
    private PaymentCondition paymentCondition;
    private Long paymentTermNum;
}
