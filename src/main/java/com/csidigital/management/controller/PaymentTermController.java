package com.csidigital.management.controller;

import com.csidigital.management.service.PaymentTermService;
import com.csidigital.shared.dto.request.PaymentTermRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/paymentCondition")
public class PaymentTermController {
    @Autowired
    private PaymentTermService paymentConditionService ;

    @GetMapping("getAllPaymentConditions")
    public List<PaymentTermResponse> getAllPaymentConditions() {

        return paymentConditionService.getAllPaymentConditions();
    }

    @GetMapping("/getPaymentConditionById/{id}")
    public PaymentTermResponse getPaymentConditionById(@PathVariable Long id){

        return paymentConditionService.getPaymentConditionById(id);
    }


    @PostMapping(value = "/createPaymentCondition", produces = "application/json")
    @ResponseBody
    public PaymentTermResponse createPaymentCondition(@Valid @RequestBody PaymentTermRequest paymentConditionRequest){
        return paymentConditionService.createPaymentCondition(paymentConditionRequest);
    }

    @PutMapping("/updatePaymentCondition/{id}")
    public PaymentTermResponse updatePaymentCondition(@Valid @RequestBody PaymentTermRequest paymentConditionRequest,
                                                      @PathVariable Long id){
        return paymentConditionService.updatePaymentCondition(paymentConditionRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaymentCondition(@PathVariable Long id){

        paymentConditionService.deletePaymentCondition(id);
    }

}
