package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.Experience;
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
public class ProfileUpdated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Integer candidateNumber;
    private String function;
    private Double total;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private Double candidateDailyCost;
    private Double totalDiscount;
    private Double profileDiscount;
    private Double tvaPercentage;
    private Double totalTva;
    @Column(length = 10000)
    private String comment;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile ;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Quotation quotation ;

    @Override
    public String toString() {
        return "ProfileUpdated{" +
                "id=" + id +
                ", candidateNumber='" + candidateNumber + '\'' +
                ", function='" + function + '\'' +
                ", total='" + total + '\'' +
                ", experience='" + experience + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", period='" + period + '\'' +
                ", candidateDailyCost='" + candidateDailyCost + '\'' +
                ", comment='" + comment + '\'' +
                ", profile='" + profile + '\'' +
                ", quotation='" + quotation + '\'' +
                '}';
    }
}
