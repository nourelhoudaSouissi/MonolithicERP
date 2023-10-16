package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OfferedService implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String title ;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType ;

    @ManyToOne
    @JsonIgnore
    private Partner partner;
}
