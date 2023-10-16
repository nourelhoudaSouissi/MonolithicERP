package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.dao.repository.PartnerSequenceRepository;
import com.csidigital.management.service.PartnerService;
import com.csidigital.shared.dto.request.PartnerCoordonneesRequest;
import com.csidigital.shared.dto.request.PartnerFinancialRequest;
import com.csidigital.shared.dto.request.PartnerFinishRequest;
import com.csidigital.shared.dto.request.PartnerRequest;
import com.csidigital.shared.dto.response.PartnerResponse;
import com.csidigital.shared.enumeration.CompanyStatus;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {
   @Autowired
   private PartnerRepository partnerRepository ;
   @Autowired
   private ModelMapper modelMapper;
   @Autowired
   private PartnerSequenceRepository sequenceRepository;
   private String partnerReference;

   @Override
   public PartnerResponse createPartner( PartnerRequest request) {

         PartnerReferenceSequence sequence = new PartnerReferenceSequence();
         sequenceRepository.save(sequence);


      Partner partner = modelMapper.map(request, Partner.class);
      CompanyStatus type = partner.getCompanyStatus();

      switch (type){
         case CLIENT: partnerReference = String.format("CL_%07d", sequence.getId());
         break;
         case PROSPECT: partnerReference = String.format("PR_%07d", sequence.getId());
         break;
         case SUPPLIER: partnerReference = String.format("FR_%07d", sequence.getId());
         break;
      }
      partner.setRef(partnerReference);
      partner.getRefs().add(partnerReference);
      // Définition de la contractDate à la date actuelle
      LocalDate currentDate = LocalDate.now();
      partner.setCreationDate(currentDate);
      /*sequence.incrementNextValue();
      sequenceRepository.save(sequence);*/
      Partner partnerSaved = partnerRepository.save(partner);
      return modelMapper.map(partnerSaved, PartnerResponse.class);
   }

   @Override
   public PartnerResponse completePartnerCoordonnées(PartnerCoordonneesRequest request) {
      Partner existingPartner = partnerRepository.findById(request.getId())
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id: " + request.getId() + " not found"));
      existingPartner.setPhoneNumber(request.getPhoneNumber());
      existingPartner.setMobilePhoneNumber(request.getMobilePhoneNumber());
      existingPartner.setEmail(request.getEmail());
      existingPartner.setWebSite(request.getWebSite());
      PartnerResponse response = modelMapper.map(existingPartner, PartnerResponse.class);
      return response;
   }

   @Override
   public PartnerResponse completePartnerFinancial(PartnerFinancialRequest request) {
      Partner existingPartner = partnerRepository.findById(request.getId())
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id: " + request.getId() + " not found"));
      existingPartner.setCurrency(request.getCurrency());
      existingPartner.setPaymentMode(request.getPaymentMode());
      existingPartner.setPaymentCondition(request.getPaymentCondition());
      PartnerResponse response = modelMapper.map(existingPartner, PartnerResponse.class);
      return response;
   }

   @Override
   public PartnerResponse finishPartner(PartnerFinishRequest request) {
      Partner existingPartner = partnerRepository.findById(request.getId())
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id: " + request.getId() + " not found"));
      existingPartner.setActivityStartDate(request.getActivityStartDate());
      existingPartner.setActivityEndDate(request.getActivityEndDate());
      existingPartner.setPartnerShipDate(request.getPartnerShipDate());
      existingPartner.setInProgressAuthorized(request.getInProgressAuthorized());
      existingPartner.setControlType(request.getControlType());
      existingPartner.setInsurancePolicy(request.getInsurancePolicy());
      existingPartner.setFoundedSince(request.getFoundedSince());
      existingPartner.setCapital(request.getCapital());
      existingPartner.setComment(request.getComment());
      existingPartner.setInsuranceCompany(request.getInsuranceCompany());
      existingPartner.setClassification(request.getClassification());
      existingPartner.setToleranceRate(request.getToleranceRate());
      PartnerResponse response = modelMapper.map(existingPartner, PartnerResponse.class);
      return response;
   }

   @Override
   public List<PartnerResponse> getAllPartners() {
      List<Partner> partners = partnerRepository.findAll();
      List<PartnerResponse> partnerList = new ArrayList<>();

      for (Partner partner : partners) {
         PartnerResponse response = modelMapper.map(partner, PartnerResponse.class);
         partnerList.add(response);
      }

      return partnerList;
   }

   @Override
   public PartnerResponse getPartnerById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      PartnerResponse partnerResponse = modelMapper.map(partner, PartnerResponse.class);
      return partnerResponse;
   }

   @Override
   public List<Requirement> getPartnerReqById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<Requirement> requirement = partner.getRequirements();
      //RequirementResponse requirementResponse = modelMapper.map(requirement, RequirementResponse.class);
      return requirement;
   }
   @Override
   public List<Address> getPartnerAddressById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<Address> address = partner.getAddresses();
      //RequirementResponse requirementResponse = modelMapper.map(requirement, RequirementResponse.class);
      return address;
   }

   @Override
   public List<Contact> getPartnerContactsById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<Contact> partnerContacts = partner.getContacts();
      //RequirementResponse requirementResponse = modelMapper.map(requirement, RequirementResponse.class);
      return partnerContacts;
   }

   /*@Override
   public List<SocialMedia> getPartnerSocialMediasById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<SocialMedia> socialMedia = partner.getSocialMedias();
      //RequirementResponse requirementResponse = modelMapper.map(requirement, RequirementResponse.class);
      return socialMedia;
   }

   @Override
   public List<OfferedService> getPartnerOfferedServicesById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<OfferedService> offeredServices = partner.getOfferedServices();
      return offeredServices;
   }*/

   @Override
   public List<BankAccount> getPartnerBankAccountsById(Long id) {
      Partner partner = partnerRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
      List<BankAccount> bankAccounts = partner.getBankAccounts();
      return bankAccounts;
   }

   @Override
   public PartnerResponse updatePartner(PartnerRequest request, Long id) {
      Partner existingPartner = partnerRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Partner with id: " + id + " not found"));

      String updatedRef = "";

      if (request.getCompanyStatus() == existingPartner.getCompanyStatus()
         || request.getCompanyStatus() == CompanyStatus.ARCHIVED) {
         updatedRef = existingPartner.getRef();
         // Use modelMapper to update remaining fields
         modelMapper.map(request, existingPartner);
         existingPartner.setRef(updatedRef);
      }
      else {
         updatedRef = getUpdatedRef(request.getCompanyStatus(), existingPartner.getRef());
         // Use modelMapper to update remaining fields
         modelMapper.map(request, existingPartner);
         existingPartner.setRef(updatedRef);
         existingPartner.getRefs().add(updatedRef);
      }

      Partner savedPartner = partnerRepository.save(existingPartner);
      return modelMapper.map(savedPartner, PartnerResponse.class);
   }

   private String getUpdatedRef(CompanyStatus companyStatus, String currentRef) {
      String prefix;

      switch (companyStatus) {
         case PROSPECT:
            prefix = "PR";
            break;
         case CLIENT:
            prefix = "CL";
            break;
         case SUPPLIER:
            prefix = "FR";
            break;
         default:
            prefix = currentRef.substring(0,2);
      }

      return prefix + currentRef.substring(2);
   }

   @Override
   public void deletePartner(Long id) {
      partnerRepository.deleteById(id);
   }



}
