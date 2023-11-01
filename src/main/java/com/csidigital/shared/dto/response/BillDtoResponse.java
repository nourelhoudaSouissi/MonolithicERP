package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.AdditionalFee;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class BillDtoResponse {
    private Long id;

    @NotBlank(message = "Invoice number cannot be blank.")
    @Column(unique = true)
    private String invoiceNumber;

    private Date date ;

    private Date dueDate;

    private Date issueDate;

    private String orderNumber ;

    private PaymentMethod PaymentMethod ;

    private String nameSeller;

    private String addressSeller;

    private String nameBuyer;

    private String addressBuyer;


    private Double totalWithDiscount;
    private Long totalDiscount;


    private Double totalAmount;

    private InvoiceEtat InvoiceEtat;

    private InvoiceType InvoiceType;

    private Currency Currency;

    private List<AdditionalFee> additionalFees ;


    // private Long collectionId;

}
