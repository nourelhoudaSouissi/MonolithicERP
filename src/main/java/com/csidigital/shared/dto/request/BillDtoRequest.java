package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.AdditionalFee;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BillDtoRequest {
    @NotBlank(message = "Invoice number cannot be blank.")
    @Column(unique = true)
    private String invoiceNumber;

    private Date date = new Date();

    private String nameSeller;

    private String addressSeller;

    private String nameBuyer = "csi digital";

    private String addressBuyer = "Bizerte";


    private Date dueDate;

    private Date issueDate;

    private String orderNumber ;

    private PaymentMethod PaymentMethod ;


    private InvoiceEtat InvoiceEtat = com.csidigital.shared.enumeration.InvoiceEtat.EN_ATTENTE;

    private InvoiceType InvoiceType;

    private Currency Currency;

    private List<AdditionalFee> additionalFees ;
    //private Long collectionId ;

}

