package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.Service;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDomainResponse {
    private Long id;
    private String title;
    private String description;
    private List<Profile> profiles ;
}
