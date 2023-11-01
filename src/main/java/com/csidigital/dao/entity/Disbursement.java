package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disbursement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private Date date ;
    private String description;
    @Enumerated(EnumType.STRING)
    private TreasuryType TreasuryType;
    @Enumerated(EnumType.STRING)
    private CategoryDisbursement categoryDisbursement ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billId", referencedColumnName = "id")
    private Bill bill;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankReconciliationId", referencedColumnName = "id")
    private BankReconciliation bankReconciliation;


    @PrePersist
    public void updateInvoiceEtat() {
        if (bill != null) {
            Bill billObject = bill;
            if (billObject != null) {
                billObject.setInvoiceEtat(InvoiceEtat.En_COURS);

            }
        }
    }





}
