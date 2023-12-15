package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Service;
import lombok.Data;

import java.util.List;

@Data
public class TvaCodeRequest {
    private String title;
    private String code;
    private String description;
    private Integer tvaValue;
    private List<Service> services ;

}
