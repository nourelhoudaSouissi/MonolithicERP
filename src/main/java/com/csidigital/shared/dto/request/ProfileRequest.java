package com.csidigital.shared.dto.request;


import lombok.Data;

@Data
public class ProfileRequest {
    private String function;
    private String experience;
    private Double candidateDailyCost;
    private String comment;
    private Long catalogNum;
}
