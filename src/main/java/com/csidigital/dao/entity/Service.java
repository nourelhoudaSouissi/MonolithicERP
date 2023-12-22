package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.CatalogType;
import com.csidigital.shared.enumeration.Experience;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String code;
    private String title;
    @Column(length = 10000)
    private String comment;

    private Double tvaPercentage;
    @Enumerated(EnumType.STRING)
    private CatalogType catalogType = CatalogType.SERVICE;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog ;

    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceUpdated> services;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tva_code_id")
    private TvaCode tvaCode ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "calculation_unit_id")
    private CalculationUnit calculationUnit ;


}
