package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EvaluationResponse {
    private Long Id ;
    private Double globalAppreciation;
    private List<OfferCandidate> offerCandidates;
    private AdministrativeData administrativeData;
    private Long employeeNum ;
    private LocalDate evaluationDate;
    private List<Interview> interviews ;

}