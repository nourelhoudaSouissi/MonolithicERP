package com.csidigital.shared.dto.request;


import lombok.Data;


@Data

public class AddressRequest {
    private int num;
    private String street ;
    private Long postalCode;
    private String city ;
    private String region;
    private String country ;
    private String type;
    private Long partnerNum;
}
