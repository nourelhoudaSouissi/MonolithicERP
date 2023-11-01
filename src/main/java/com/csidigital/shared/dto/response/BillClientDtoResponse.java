package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.FeeClient;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class BillClientDtoResponse {

    private Long id;

    @NotBlank(message = "Invoice number cannot be blank.")
    @Column(unique = true)
    private String invoiceNumber;

    private Date date ;

    private Date dueDate;

    private String orderNumber ;

    private com.csidigital.shared.enumeration.PaymentMethod PaymentMethod ;

    private String nameSeller;

    private String addressSeller;

    private String nameBuyer;

    private String addressBuyer;

    private Long totalDiscount;


    private Double totalWithDiscount;

    private Double totalAmount;

    private InvoiceEtat InvoiceEtat;

    private InvoiceType InvoiceType;

    private Currency Currency;

    @Lob
    private String logo ;
    // private Long treasuryId ;

    private List<FeeClient> feeClients ;
}
