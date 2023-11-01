package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"invoiceNumber"}))

public class Bill  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Invoice number cannot be blank.")
    @Column(unique = true)
    private String invoiceNumber;

    private Date date ;

    private Date dueDate;

    private Date issueDate;

    private String orderNumber ;


    private String nameSeller;

    private String addressSeller;

    private String nameBuyer;

    private String addressBuyer;

    private Double totalWithDiscount;

    @Transient
    private Long totalDiscount;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod PaymentMethod ;

    @Enumerated(EnumType.STRING)

    private InvoiceEtat InvoiceEtat;

    @Enumerated(EnumType.STRING)
    private InvoiceType InvoiceType;

    public double getTotalAmount() {
        double total = 0;
        for (AdditionalFee additionalFee : additionalFees) {
            total += additionalFee.getPriceWithAllTaxIncluded();
        }
        return total;
    }


    public double getTotalDiscount() {
        int count = 0;
        double total = 0;

        for (AdditionalFee additionalFee : additionalFees) {
            total += additionalFee.getDiscount();
            count++;
        }

        if (count > 0) {
            return total / count;
        } else {
            return 0;
        }
    }


    public double getTotalWithDiscount() {
        double total = 0;
        double total1=0 ;
        for (AdditionalFee additionalFee : additionalFees) {
            total += additionalFee.getPriceWithAllTaxIncluded();
        }
        total1 =total - (total * getTotalDiscount() / 100) ;
        return total1;
    }


    @Enumerated(EnumType.STRING)

    private Currency Currency;

    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<AdditionalFee> additionalFees;

    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
    private Disbursement disbursement;




}
