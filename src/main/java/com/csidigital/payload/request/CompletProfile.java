package com.csidigital.payload.request;

import lombok.Data;

@Data

public class CompletProfile {
    private String username;
    private String fullname;

    private long phone;

    private String adresse;

    private String website;


}
