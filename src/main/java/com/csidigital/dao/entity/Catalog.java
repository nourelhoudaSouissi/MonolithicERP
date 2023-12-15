package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String ref;
    @Column(length = 10000)
    private String comment;
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @JsonIgnore
    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private List<Profile> profiles;

    @JsonIgnore
    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private List<Service> services;

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                "startDate=" + startDate +
                "endDate=" + endDate +
                "title=" +title+
                "ref=" +ref+
                "comment=" +comment+
                "creationDate=" +creationDate+
                "currency=" +currency+
                "profiles=" +profiles+
                '}';
    }
}
