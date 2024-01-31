package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.Experience;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReference  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String function;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private Double yearsOfExperience;
    private String technologie;
    @Column(length = 10000)
    private String description;
}
