package com.csidigital.management.service;

import com.csidigital.shared.dto.request.QuotationRequest;
import com.csidigital.shared.dto.response.QuotationResponse;
import com.csidigital.shared.dto.response.RequirementResponse;

import java.util.List;

public interface QuotationService {
    QuotationResponse createQuotation(QuotationRequest quotationRequest);
    List<QuotationResponse> getAllQuotations();
    QuotationResponse getQuotationById(Long id);

    QuotationResponse updateQuotation(QuotationRequest quotationRequest , Long id );
    //Double calculateQuotationRevenue(Quotation quotation);
    void updateStatusToAccepted(Long id);
    void updateStatusToInProgress(Long id);
    void updateStatusToRefused(Long id);
    void updateStatusToUnanswered(Long id);
    void updateStatusToSentToClient(Long id);
    RequirementResponse getReqByQotId(Long qotId);
    void deleteQuotation(Long id);
}
