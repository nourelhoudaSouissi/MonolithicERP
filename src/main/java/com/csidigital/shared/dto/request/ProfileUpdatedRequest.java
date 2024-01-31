package com.csidigital.shared.dto.request;


import com.csidigital.dao.entity.Profile;
import com.csidigital.management.service.impl.ProfileServiceImpl;
import com.csidigital.shared.dto.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class ProfileUpdatedRequest {
    private ModelMapper modelMapper;
    private ProfileServiceImpl profileService;
    private Integer candidateNumber;
    private Double candidateDailyCost;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private String comment;
    private Profile profile;
    private Long profileNum;
    private Long quotationNum;
    private Double totalDiscount;
    private Double profileDiscount;
    private Double tvaPercentage;
    private Double totalTva;

    void profile(){
        if(this.profileNum != null){
            ProfileResponse profileRes = profileService.getProfileById(this.profileNum);
            Profile profile = modelMapper.map(profileRes, Profile.class);
            this.setProfile(profile);
        }
    }
}
