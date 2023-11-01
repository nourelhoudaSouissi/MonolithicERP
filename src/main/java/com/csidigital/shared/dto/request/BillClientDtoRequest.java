package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.FeeClient;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BillClientDtoRequest {
    @NotBlank(message = "Invoice number cannot be blank.")
    @Column(unique = true)
    private String invoiceNumber;

    private Date date = new Date();

    private String nameSeller = "csi digital";

    private String addressSeller = "Bizerte";

    private String nameBuyer;

    private String addressBuyer;


    private Date dueDate;

    private String orderNumber ;


    private InvoiceEtat InvoiceEtat = com.csidigital.shared.enumeration.InvoiceEtat.EN_ATTENTE;

    private InvoiceType InvoiceType;

    private PaymentMethod PaymentMethod ;

    private Currency Currency;

    @Lob
    private String logo ;

    private List<FeeClient> feeClients ;

}
