package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalFee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String refFree;
    private String designation;
    private String unite;
    private Double cost;
    private Long tva;
    private Long discount;
    private Integer quantity;
    private Double priceWithoutTax;

    private Double priceWithAllTaxIncluded;

    public Double getPriceWithoutTax() {
        return cost * quantity;
    }

    public Double getPriceWithAllTaxIncluded() {
        return (cost * quantity) + ((cost * quantity) * tva / 100);
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    @JsonBackReference

    private Bill bill;



}
