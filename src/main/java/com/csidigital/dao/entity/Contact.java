package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.Civility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
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
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    @Enumerated(EnumType.STRING)
    private Civility civility;
    private String lastName;
    private String firstName;
    private String fullName;
    private String function;
    private String societe ;
    private String localisation;
    private String service;
    private String email;
    private Long phoneNumber;
    private Long mobilePhoneNumber;
    private boolean privilegedContact;
    private boolean appointmentMaking;
    @Column(length = 10000)
    private String comment;

    private String privilege;
    @JsonIgnore
    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    private Partner partner ;


    @JsonIgnore
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<ContactNote> contactNotes;

    @JsonIgnore
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @PrePersist
    @PreUpdate
    public void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }
}
