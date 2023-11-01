package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.RapprochementEtat;
import lombok.Data;

import java.util.Date;

@Data
public class BankReconciliationDtoRequest {
    private String bankNumber;
    private Date issueDate;
    private String company;
    private String iban;
    private Date completionDate;
    private RapprochementEtat rapprochementEtat = RapprochementEtat.NON_RAPPROCHÉ;

}
