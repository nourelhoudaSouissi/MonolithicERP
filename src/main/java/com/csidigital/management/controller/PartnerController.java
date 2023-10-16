package com.csidigital.management.controller;

import com.csidigital.dao.entity.Address;
import com.csidigital.dao.entity.BankAccount;
import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.Requirement;
import com.csidigital.management.service.impl.PartnerServiceImpl;
import com.csidigital.shared.dto.request.PartnerCoordonneesRequest;
import com.csidigital.shared.dto.request.PartnerFinancialRequest;
import com.csidigital.shared.dto.request.PartnerFinishRequest;
import com.csidigital.shared.dto.request.PartnerRequest;
import com.csidigital.shared.dto.response.PartnerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/partners")
//@CrossOrigin(origins = "${cross.origin.url}")
public class PartnerController {
    @Autowired
    private PartnerServiceImpl partnerService ;

    @GetMapping()
    public List<PartnerResponse> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public PartnerResponse getPartnerById(@PathVariable Long id){
        return partnerService.getPartnerById(id);
    }
    @GetMapping("/{id}/requirements")
    public List<Requirement> getPartnerReqById(@PathVariable Long id){
        return partnerService.getPartnerReqById(id);
    }
    @GetMapping("/{id}/contacts")
    public List<Contact> getPartnerContactById(@PathVariable Long id){
        return partnerService.getPartnerContactsById(id);
    }
    @GetMapping("/{id}/addresses")
    public List<Address> getPartnerAddressById(@PathVariable Long id){
        return partnerService.getPartnerAddressById(id);
    }
    /*@GetMapping("/{id}/socialMedias")
    public List<SocialMedia> getPartnerSocialMediasById(@PathVariable Long id){
        return partnerService.getPartnerSocialMediasById(id);
    }

    @GetMapping("/{id}/offered")
    public List<OfferedService> getPartnerOfferedServicesById(@PathVariable Long id){
        return partnerService.getPartnerOfferedServicesById(id);
    }*/

    @GetMapping("/{id}/bankAccounts")
    public List<BankAccount> getPartnerAccountsById(@PathVariable Long id){
        return partnerService.getPartnerBankAccountsById(id);
    }

    @PostMapping()
    public PartnerResponse createPartner(@RequestBody PartnerRequest partnerRequest){


        return partnerService.createPartner( partnerRequest);
    }

    @PostMapping("/coordonnees")
    public PartnerResponse completePartnerCoordonnées(@RequestBody PartnerCoordonneesRequest request){
        return partnerService.completePartnerCoordonnées(request);
    }

    @PostMapping("/financial")
    public PartnerResponse completePartnerFinancial(@RequestBody PartnerFinancialRequest request){
        return partnerService.completePartnerFinancial(request);
    }

    @PostMapping("/final")
    public PartnerResponse finishPartner(@RequestBody PartnerFinishRequest request){
        return partnerService.finishPartner(request);
    }

    @PutMapping("/{id}")
    public PartnerResponse updatePartner(@Valid @RequestBody PartnerRequest partnerRequest,
                                                 @PathVariable Long id){
        return partnerService.updatePartner(partnerRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deletePartner(@PathVariable Long id){
        partnerService.deletePartner(id);
    }


}
