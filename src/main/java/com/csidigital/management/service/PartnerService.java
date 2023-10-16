package com.csidigital.management.service;

import com.csidigital.dao.entity.Address;
import com.csidigital.dao.entity.BankAccount;
import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.Requirement;
import com.csidigital.shared.dto.request.PartnerCoordonneesRequest;
import com.csidigital.shared.dto.request.PartnerFinancialRequest;
import com.csidigital.shared.dto.request.PartnerFinishRequest;
import com.csidigital.shared.dto.request.PartnerRequest;
import com.csidigital.shared.dto.response.PartnerResponse;

import java.util.List;

public interface PartnerService {
    PartnerResponse createPartner( PartnerRequest request);
    PartnerResponse completePartnerCoordonnées(PartnerCoordonneesRequest request);
    PartnerResponse completePartnerFinancial(PartnerFinancialRequest request);
    PartnerResponse finishPartner(PartnerFinishRequest request);
    List<PartnerResponse> getAllPartners();
    PartnerResponse getPartnerById(Long id);
     List<Requirement> getPartnerReqById(Long id) ;
    List<Address> getPartnerAddressById(Long id);
    List<Contact> getPartnerContactsById(Long id) ;
    /*List<SocialMedia> getPartnerSocialMediasById(Long id);
    List<OfferedService> getPartnerOfferedServicesById(Long id);*/
    List<BankAccount> getPartnerBankAccountsById(Long id);
    PartnerResponse updatePartner(PartnerRequest partnerRequest , Long id );
    void deletePartner(Long id);
}
