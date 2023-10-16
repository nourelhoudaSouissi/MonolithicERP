package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.SocialMediaName;
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
public class SocialMedia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SocialMediaName name;
    private String link;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Partner partner;
}