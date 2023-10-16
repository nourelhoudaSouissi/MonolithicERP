package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.Experience;
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
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String function;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private Double candidateDailyCost;
    @Column(length = 10000)
    private String comment;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog ;
    @JsonIgnore
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileUpdated> profiles;

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", function='" + function + '\'' +
                ", experience='" + experience + '\'' +
                ", candidateDailyCost='" + candidateDailyCost + '\'' +
                ", comment='" + comment + '\'' +
                ", profiles='" + profiles + '\'' +
                ", catalog='" + catalog + '\'' +
                '}';
    }
}
