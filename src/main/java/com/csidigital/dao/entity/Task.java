package com.csidigital.dao.entity;



import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate EndDate;
    private Long estimation;
    private Long progression ;
    private Long TimeSpent ;
    private LocalDate creationDate ;
    private LocalDate realEndDate ;
    private LocalDate projectionDate;
    private TaskType taskType;
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private TaskPhase taskPhase;
    @Column(unique = true)
    private String taskReference;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;
    @JsonIgnore
    @OneToMany(mappedBy = "task" , cascade = CascadeType.ALL)
    private List<SubTask> SubTaskList ;

}
