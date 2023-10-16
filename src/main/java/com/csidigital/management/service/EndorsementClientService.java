package com.csidigital.management.service;

import com.csidigital.shared.dto.request.EndorsementClientRequest;
import com.csidigital.shared.dto.response.EndorsementClientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EndorsementClientService {
    EndorsementClientResponse createEndorsement(EndorsementClientRequest request);
    List<EndorsementClientResponse> getAllEndorsements();
    EndorsementClientResponse getEndorsementById(Long id);

    EndorsementClientResponse updateEndorsement(EndorsementClientRequest request, Long id);

    void deleteEndorsement(Long id);
}
