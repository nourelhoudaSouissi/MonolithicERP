package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.CatalogType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUpdated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String code;
    private String title;
    @Enumerated(EnumType.STRING)
    private CatalogType catalogType;


    /*  For the quotation  */
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private Integer serviceQuantity;
    private Double total;
    private Double totalTva;

    private Double tvaPercentage;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service ;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Quotation quotation ;


}
