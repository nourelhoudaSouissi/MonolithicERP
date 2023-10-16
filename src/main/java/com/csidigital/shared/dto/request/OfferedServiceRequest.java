package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.ServiceType;
import lombok.Data;


@Data

public class OfferedServiceRequest {
    private String title ;

    private ServiceType serviceType ;

    private Long partnerNum;
}
