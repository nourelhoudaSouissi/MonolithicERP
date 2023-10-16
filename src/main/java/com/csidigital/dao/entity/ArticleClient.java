package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "articleNumber")
    private Integer articleNumber;
    @Column(name = "articleTitle")
    private String articleTitle;
    @Column(name = "description" , length = 100000)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_contract")
    private ContractClient contractClient;

}
