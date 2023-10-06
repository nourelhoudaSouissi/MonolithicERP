package com.csidigital.controllers;

import com.csidigital.models.Customer;
import com.csidigital.models.Partner;
import com.csidigital.models.User;
import com.csidigital.repository.CustomerRepository;
import com.csidigital.repository.PartnerRepository;
import com.csidigital.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/part")
public class PartnerController {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;


    // Create a new Partner
    @GetMapping("/getCurrentUser")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("username");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Long userId = optionalUser.get().getId();
        System.out.println("id");
        System.out.println(userId);
        System.out.println("testPartner");
        System.out.println(username);
        return optionalUser.orElse(null);
    }
    @GetMapping("/getCurrentCustomer")
    public Customer getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        return optionalCustomer.orElse(null);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_CUSTOMER_USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createPartner(@RequestBody Partner partner) {
        try {
            Customer currentCustomer = getCurrentCustomer();

            if (currentCustomer != null) {
                partner.setUserp(currentCustomer.getUser());
                Partner createdPartner = partnerRepository.save(partner);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdPartner);
            } else {
                User currentUser = getCurrentUser();
                if (currentUser != null) {
                    partner.setUserp(currentUser);
                    Partner createdPartner = partnerRepository.save(partner);
                    return ResponseEntity.status(HttpStatus.CREATED).body(createdPartner);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // Get all partners



        // Read a specific Partner by ID
        @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_CUSTOMER_USER')")
        @GetMapping("/partners")
        public List<Partner> getAllPartners() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
            if (optionalCustomer.isPresent()) {
                // Current authenticated user is a customer
                Customer currentCustomer = optionalCustomer.get();
                return partnerRepository.findByUserp(currentCustomer.getUser());
            } else {
                Optional<User> optionalUser = userRepository.findByUsername(username);
                if (optionalUser.isPresent()) {
                    // Current authenticated user is not a customer but a regular user
                    User currentUser = optionalUser.get();
                    return partnerRepository.findByUserp(currentUser);
                } else {
                    // User not found
                    throw new RuntimeException("User not found.");
                }
            }
        }


// ...
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_CUSTOMER_USER')")
    @DeleteMapping("/partners/{id}")
    @Transactional
    public ResponseEntity<?> deletePartner(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            // Current authenticated user is a customer
            Customer currentCustomer = optionalCustomer.get();
            partnerRepository.deleteByIdAndUserpId(id, currentCustomer.getUser().getId());
            return ResponseEntity.ok("Partner deleted successfully.");
        } else {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                // Current authenticated user is not a customer but a regular user
                User currentUser = optionalUser.get();
                partnerRepository.deleteByIdAndUserpId(id, currentUser.getId());
                return ResponseEntity.ok("Partner deleted successfully.");
            }
        }

        // Partner not found or user not authorized
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found or user not authorized.");
    }



    // Retrieve a specific Partner
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_CUSTOMER_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPartnerById(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            // Current authenticated user is a customer
            Customer currentCustomer = optionalCustomer.get();
            Optional<Partner> optionalPartner = partnerRepository.findByIdAndUserpId(id, currentCustomer.getUser().getId());
            if (optionalPartner.isPresent()) {
                Partner partner = optionalPartner.get();
                return ResponseEntity.ok(partner);
            }
        } else {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                // Current authenticated user is not a customer but a regular user
                User currentUser = optionalUser.get();
                Optional<Partner> optionalPartner = partnerRepository.findByIdAndUserpId(id, currentUser.getId());
                if (optionalPartner.isPresent()) {
                    Partner partner = optionalPartner.get();
                    return ResponseEntity.ok(partner);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found or user not authorized.");
    }

    // Update a Partner
    // Update a Partner
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_CUSTOMER_USER')")
    @PutMapping("/update{id}")
    public ResponseEntity<?> updatePartner(@PathVariable("id") Long id, @RequestBody Partner updatedPartner) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            // Current authenticated user is a customer
            Customer currentCustomer = optionalCustomer.get();
            Optional<Partner> optionalPartner = partnerRepository.findByIdAndUserpId(id, currentCustomer.getUser().getId());
            if (optionalPartner.isPresent()) {
                Partner partner = optionalPartner.get();
                partner.setName(updatedPartner.getName());
                partner.setCompanyStatus(updatedPartner.getCompanyStatus());
                partner.setStaffNumber(updatedPartner.getStaffNumber());
                partner.setParentCompany(updatedPartner.getParentCompany());
                partner.setCeoName(updatedPartner.getCeoName());
                partner.setCeoLastName(updatedPartner.getCeoLastName());
                partner.setPhoneNumber(updatedPartner.getPhoneNumber());
                partner.setPhoneNumberTwo(updatedPartner.getPhoneNumberTwo());
                partner.setRefPhoneNumber(updatedPartner.getRefPhoneNumber());
                partner.setRefPhoneNumber2(updatedPartner.getRefPhoneNumber2());
                partner.setPostCode(updatedPartner.getPostCode());
                partner.setCity(updatedPartner.getCity());
                partner.setCountry(updatedPartner.getCountry());
                partner.setDescription(updatedPartner.getDescription());
                partner.setWorkField(updatedPartner.getWorkField());
                partner.setLegalStatus(updatedPartner.getLegalStatus());
                partner.setActivityStartDate(updatedPartner.getActivityStartDate());
                partner.setActivityEndDate(updatedPartner.getActivityEndDate());
                partner.setPartnerShipDate(updatedPartner.getPartnerShipDate());
                partner.setProvenance(updatedPartner.getProvenance());

                Partner updatedPartnerData = partnerRepository.save(partner);
                return ResponseEntity.ok(updatedPartnerData);
            }
        } else {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                // Current authenticated user is not a customer but a regular user
                User currentUser = optionalUser.get();
                Optional<Partner> optionalPartner = partnerRepository.findByIdAndUserpId(id, currentUser.getId());
                if (optionalPartner.isPresent()) {
                    Partner partner = optionalPartner.get();
                    partner.setName(updatedPartner.getName());
                    partner.setCompanyStatus(updatedPartner.getCompanyStatus());
                    partner.setStaffNumber(updatedPartner.getStaffNumber());
                    partner.setParentCompany(updatedPartner.getParentCompany());
                    partner.setCeoName(updatedPartner.getCeoName());
                    partner.setCeoLastName(updatedPartner.getCeoLastName());
                    partner.setPhoneNumber(updatedPartner.getPhoneNumber());
                    partner.setPhoneNumberTwo(updatedPartner.getPhoneNumberTwo());
                    partner.setRefPhoneNumber(updatedPartner.getRefPhoneNumber());
                    partner.setRefPhoneNumber2(updatedPartner.getRefPhoneNumber2());
                    partner.setPostCode(updatedPartner.getPostCode());
                    partner.setCity(updatedPartner.getCity());
                    partner.setCountry(updatedPartner.getCountry());
                    partner.setDescription(updatedPartner.getDescription());
                    partner.setWorkField(updatedPartner.getWorkField());
                    partner.setLegalStatus(updatedPartner.getLegalStatus());
                    partner.setActivityStartDate(updatedPartner.getActivityStartDate());
                    partner.setActivityEndDate(updatedPartner.getActivityEndDate());
                    partner.setPartnerShipDate(updatedPartner.getPartnerShipDate());
                    partner.setProvenance(updatedPartner.getProvenance());

                    Partner updatedPartnerData = partnerRepository.save(partner);
                    return ResponseEntity.ok(updatedPartnerData);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found or user not authorized.");
    }


}
