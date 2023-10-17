package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ResponsableExtern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstName ;
    private String lastName;
    private String addressMail ;
    private String phoneNumber ;
    private String function ;

    @JsonIgnore
    @ManyToOne
    private Project project ;

}
