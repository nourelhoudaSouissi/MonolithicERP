package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EvaluationRequest {
    private Double globalAppreciation;
    private List<OfferCandidate>offerCandidates;
    private List<Interview> interviews;
    private LocalDate evaluationDate;
    private Long employeeNum ;

}