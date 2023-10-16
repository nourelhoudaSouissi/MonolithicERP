package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.ContractClientType;
import com.csidigital.shared.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContractClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    @Column(name="titleContract")
    private String titleContract;

    @Column(name="dateContract")
    private LocalDate dateContract;
    @Column(name="lieuContract")
    private String lieuContract;
    @Column(name="startDate")
    private LocalDate startDate;
    @Column(name="endDate")
    private LocalDate endDate;
    @Column(name="introductionSoc", length = 100000)
    private String introductionSoc;
    @Column(name="introductionClient", length = 100000)
    private String introductionClient;
    @Enumerated(EnumType.STRING)
    private Status contractStatus;
    @Enumerated(EnumType.STRING)
    private ContractClientType contractType;

    @OneToMany(mappedBy = "contractClient", cascade = CascadeType.ALL)
    private List<ArticleClient>  articleClients;

    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<EndorsementClient> endorsementClients;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner ;


}
