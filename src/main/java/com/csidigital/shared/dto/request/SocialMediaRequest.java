package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.SocialMediaName;
import lombok.Data;

@Data
public class SocialMediaRequest {
    private SocialMediaName name;
    private String link;
    private Long partnerNum;
}
