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
public class BankReconciliation implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String bankNumber;
    private Date issueDate;
    private String company;
    private String iban;
    private Date completionDate;

    @OneToOne(mappedBy = "bankReconciliation", cascade = CascadeType.ALL)
    private Disbursement disbursement;

    @OneToOne(mappedBy = "bankReconciliation", cascade = CascadeType.ALL)
    private Collection collection;



    @Enumerated(EnumType.STRING)
    private RapprochementEtat rapprochementEtat;

    @PrePersist
    public void updateCollectionEtat() {
        if (rapprochementEtat == RapprochementEtat.RAPPROCHÉ && collection != null) {
            collection.setTreasuryType(TreasuryType.SOLDÉ);
        }
    }

}
