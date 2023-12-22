package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import lombok.Data;

import java.util.List;

@Data
public class CalculationUnitRequest {
    private String title;
    private String code;
    private String description;
    private List<Service> services ;
    private List<Profile> profiles ;
}
