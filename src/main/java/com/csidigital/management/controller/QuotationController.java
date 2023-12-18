package com.csidigital.management.controller;

import com.csidigital.management.service.impl.QuotationServiceImpl;
import com.csidigital.shared.dto.request.QuotationRequest;
import com.csidigital.shared.dto.response.QuotationResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/quotations")
//@CrossOrigin(origins = "${cross.origin.url}")
public class QuotationController {
    @Autowired
    private QuotationServiceImpl quotationService ;
    @GetMapping("/req/{qotId}")
    public RequirementResponse getReqByQotId(@PathVariable Long qotId){
        return quotationService.getReqByQotId(qotId);
    }

    @GetMapping()
    public List<QuotationResponse> getAllQuotations() {
        return quotationService.getAllQuotations();
    }

    @GetMapping("/{id}")
    public QuotationResponse getQuotationById(@PathVariable Long id){
        return quotationService.getQuotationById(id);
    }

    @PostMapping()
    public QuotationResponse createQuotation(@Valid @RequestBody QuotationRequest quotationRequest){
        System.out.println(quotationRequest.getRequirementNum());
        System.out.println(quotationRequest.getProfiles());
        return quotationService.createQuotation(quotationRequest);
    }

    @PutMapping("/{id}")
    public QuotationResponse updateQuotation(@Valid @RequestBody QuotationRequest request,
                                     @PathVariable Long id){
        return quotationService.updateQuotation(request, id);
    }

    @PutMapping("/updateToInProgress/{id}")
    void updateStatusToInProgress(@PathVariable Long id) {
        quotationService.updateStatusToInProgress(id);
    }
    @PutMapping("/updateToAccepted/{id}")
    void updateStatusToAccepted(@PathVariable Long id) {
        quotationService.updateStatusToAccepted(id);
    }
    @PutMapping("/updateToRefused/{id}")
    void updateStatusToRefused(@PathVariable Long id) {
        quotationService.updateStatusToRefused(id);
    }

    @PutMapping("/updateToUnanswered/{id}")
    void updateStatusToUnanswered(@PathVariable Long id) {
        quotationService.updateStatusToUnanswered(id);
    }
    @PutMapping("/updateToSentToClient/{id}")
    void updateStatusToSentToClient(@PathVariable Long id) {
        quotationService.updateStatusToSentToClient(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuotation(@PathVariable Long id){
        quotationService.deleteQuotation(id);
    }
}
