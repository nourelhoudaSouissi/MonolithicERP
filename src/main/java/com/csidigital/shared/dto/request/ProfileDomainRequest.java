package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDomainRequest {
    private String title;
    private String description;

    private List<Profile> profiles ;

}
