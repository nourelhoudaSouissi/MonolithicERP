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
public class Collection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category ;
    private Date date;
    private String description ;
    @Enumerated(EnumType.STRING)
    private TreasuryType treasuryType;
    @Enumerated(EnumType.STRING)
    private CategoryCollection CategoryCollection ;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billClientId", referencedColumnName = "id")
    private BillClient billClient;

    @PrePersist
    public void updateInvoiceEtat() {
        if (billClient != null) {
            BillClient bill = billClient;
            if (bill != null) {
                bill.setInvoiceEtat(InvoiceEtat.En_COURS);

            }
        }
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankReconciliationId", referencedColumnName = "id")
    private BankReconciliation bankReconciliation;

}





