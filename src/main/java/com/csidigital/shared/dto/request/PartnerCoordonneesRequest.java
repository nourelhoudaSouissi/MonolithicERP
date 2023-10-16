package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class PartnerCoordonneesRequest {
    private Long id;
    private Long phoneNumber ;
    private Long mobilePhoneNumber ;
    private String email;
    private String webSite;
}
