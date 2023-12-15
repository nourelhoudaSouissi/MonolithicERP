package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import com.csidigital.shared.enumeration.Currency;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CatalogResponse {
    private Long id;
    private int year;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String ref;
    private String creationDate;
    private String comment;
    private Currency currency;
    private List<ProfileResponse> profiles;
    private List<ServiceResponse> services;

}
