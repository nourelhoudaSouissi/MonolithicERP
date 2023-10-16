package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.ServiceType;
import lombok.Data;

@Data
public class OfferedServiceResponse {
    private Long id ;
    private String title ;

    private ServiceType serviceType ;


    private Long partnerId;
}
