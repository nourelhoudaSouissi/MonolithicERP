package com.csidigital.management.controller;

import com.csidigital.management.service.impl.AddressServiceImpl;
import com.csidigital.shared.dto.request.AddressRequest;
import com.csidigital.shared.dto.response.AddressResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/addresses")
//@CrossOrigin("${cross.origin.url}")
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService ;

    @GetMapping()
    public List<AddressResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping()
    public AddressResponse createAddress(@Valid @RequestBody AddressRequest addressRequest){
        return addressService.createAddress(addressRequest);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(@Valid @RequestBody AddressRequest addressRequest,
                                                 @PathVariable Long id){
        return addressService.updateAddress(addressRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
    }

}
