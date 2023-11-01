package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.RapprochementEtat;
import lombok.Data;

import java.util.Date;


@Data
public class BankReconciliationDtoResponse  {
        private Long id;
        private String bankNumber;

        private Date issueDate;

        private String company;
        private String iban;
        private Date completionDate;
        private CollectionDtoResponse collection;
        private DisbursementDtoResponse disbursement;
        private RapprochementEtat rapprochementEtat;

}
