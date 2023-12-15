package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import com.csidigital.shared.enumeration.Currency;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CatalogRequest {
    private int year;
    private String ref;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String comment;
    private Currency currency;
    private List<ProfileRequest> profiles;
    private List<ServiceRequest> services;

}
