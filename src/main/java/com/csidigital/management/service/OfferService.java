package com.csidigital.management.service;
import com.csidigital.shared.dto.request.OfferRequest;
import com.csidigital.shared.dto.response.OfferResponse;

import java.util.List;

public interface OfferService {
    OfferResponse createOffer(OfferRequest request);
    List<OfferResponse> getAllOffer();
    OfferResponse getOfferById(Long id);

    OfferResponse updateOffer(OfferRequest request, Long id);

    void deleteOffer(Long id);

}

