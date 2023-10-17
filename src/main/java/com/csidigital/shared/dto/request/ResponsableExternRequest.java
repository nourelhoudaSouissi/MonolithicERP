package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class ResponsableExternRequest {
    private String firstName ;
    private String lastName;
    private String addressMail ;
    private String phoneNumber ;
    private String function ;
}
