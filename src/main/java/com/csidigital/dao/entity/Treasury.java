package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.CategoryCaisse;
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
public class Treasury implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount ;
    private Date date;
    private String description ;
    @Enumerated(EnumType.STRING)
    private CategoryCaisse categoryCaisse;









}
