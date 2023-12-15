package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import com.csidigital.management.service.impl.ProfileServiceImpl;
import com.csidigital.management.service.impl.ServiceServiceImpl;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.enumeration.CatalogType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ServiceUpdatedRequest {
    private Double amount;
    private String code;
    private String title;
    private CatalogType catalogType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private Integer serviceQuantity;
    private Double total;
    private Double tvaPercentage;
    private Double totalTva;

    private Long quotationNum;
    private Long serviceNum;
    private Service service;




    private ModelMapper modelMapper;
    private ServiceServiceImpl serviceServiceImpl;
    void service(){
        if(this.serviceNum != null){
            ServiceResponse serviceRes = serviceServiceImpl.getServiceById(this.serviceNum);
            Service service = modelMapper.map(serviceRes, Service.class);
            this.setService(service);
        }
    }
}
