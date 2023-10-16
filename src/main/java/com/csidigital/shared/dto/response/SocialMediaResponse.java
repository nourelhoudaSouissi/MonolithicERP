package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.SocialMediaName;
import lombok.Data;

@Data
public class SocialMediaResponse {
    private Long id;
    private SocialMediaName name;
    private String link;
    private Long partnerId;
}