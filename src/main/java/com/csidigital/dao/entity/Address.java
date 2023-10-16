package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private int num;
    private String street ;
    private Long postalCode;
    private String city ;
    private String region;
    private String country ;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Partner partner;
}
