package com.csidigital.management.service;


import com.csidigital.shared.dto.request.EndorsementRequest;
import com.csidigital.shared.dto.response.EndorsementResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EndorsementService {

    EndorsementResponse createEndorsement(EndorsementRequest request);
    List<EndorsementResponse> getAllEndorsements();
    EndorsementResponse getEndorsementById(Long id);

    EndorsementResponse updateEndorsement(EndorsementRequest request, Long id);

    void deleteEndorsement(Long id);
    void updateStatusById(Long id, String status);
    void updateStatusToAcceptedById(Long id);
    void updateStatusToRefusedById(Long id);
    void updateStatusToExpiredById(Long id);
    void updateStatusToSentById(Long id);


    //Statistiques
    int countStillPendingStatus();

    int countRefusedStatus();

    int countAcceptedStatus();

    int countSentStatus();

    int countExpiredStatus();
}
