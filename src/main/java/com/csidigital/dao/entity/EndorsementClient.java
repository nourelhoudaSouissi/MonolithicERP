package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndorsementClient implements Serializable {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title")
    private String title;
    @Column(name = "reference")
    private String reference;
    @Column(name = "endorsementDate")
    private LocalDate endorsementDate;
    @Column(name = "object")
    private String object;
    @Column(name = "note",length = 5000)
    private String note;
    @JsonIgnore
    @ManyToOne
    private ContractClient contract;
}
