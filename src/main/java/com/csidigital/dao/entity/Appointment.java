package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private Long duration;
    private String subject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Contact contact;

    @JsonIgnore
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<AppointmentNote> notes;
}
