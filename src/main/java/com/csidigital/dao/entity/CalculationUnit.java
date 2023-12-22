package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CalculationUnit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private String description;


    @JsonIgnore
    @OneToMany(mappedBy = "calculationUnit", cascade = CascadeType.ALL)
    private List<Service> services ;

    @JsonIgnore
    @OneToMany(mappedBy = "calculationUnit", cascade = CascadeType.ALL)
    private List<Profile> profiles ;

}
