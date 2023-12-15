package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.Service;
import lombok.Data;

import java.util.List;

@Data
public class TvaCodeResponse {
    private Long id;
    private String title;
    private String code;
    private String description;
    private Integer tvaValue;
    private List<Service> services ;

}
