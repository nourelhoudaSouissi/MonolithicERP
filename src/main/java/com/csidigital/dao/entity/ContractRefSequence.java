package com.csidigital.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContractRefSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nextValue;

    public ContractRefSequence() {
        //this.nextValue = 1L;  Initialize with the starting value
    }

    /*public Long getNextValue() {
        return nextValue;
    }

    public void incrementNextValue() {
        nextValue++;
    }*/
}
