package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.ArticleClient;
import com.csidigital.dao.entity.EndorsementClient;
import com.csidigital.shared.enumeration.ContractClientType;
import com.csidigital.shared.enumeration.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContractClientResponse {
    private Long id;
    private String titleContract;
    private LocalDate dateContract;
    private String reference;
    private String lieuContract;

    private LocalDate startDate;

    private LocalDate endDate;
    private String introductionSoc;

    private String introductionClient;

    private ContractClientType contractType;
    private Status contractStatus;

    private List<ArticleClient>  articleClients;
    private List<EndorsementClient> endorsementClients;
    private Long partnerId;
}
