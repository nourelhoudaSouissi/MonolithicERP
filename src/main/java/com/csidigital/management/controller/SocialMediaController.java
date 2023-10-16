package com.csidigital.management.controller;

import com.csidigital.management.service.impl.SocialMediaServiceImpl;
import com.csidigital.shared.dto.request.SocialMediaRequest;
import com.csidigital.shared.dto.response.SocialMediaResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/socialMedias")
//@CrossOrigin(origins = "${cross.origin.url}")
public class SocialMediaController {
    @Autowired
    private SocialMediaServiceImpl socialMediaService ;

    @GetMapping()
    public List<SocialMediaResponse> getAllSocialMedias() {
        return socialMediaService.getAllSocialMedias();
    }

    @GetMapping("/{id}")
    public SocialMediaResponse getSocialMediaById(@PathVariable Long id){
        return socialMediaService.getSocialMediaById(id);
    }

    @PostMapping()
    public SocialMediaResponse createSocialMedia(@Valid @RequestBody SocialMediaRequest socialMediaRequest ){
        return socialMediaService.createSocialMedia(socialMediaRequest );
    }

    @PutMapping("/{id}")
    public SocialMediaResponse updateSocialMedia(@Valid @RequestBody SocialMediaRequest socialMediaRequest,
                                                 @PathVariable Long id){
        return socialMediaService.updateSocialMedia(socialMediaRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSocialMedia(@PathVariable Long id){
        socialMediaService.deleteSocialMedia(id);
    }
}