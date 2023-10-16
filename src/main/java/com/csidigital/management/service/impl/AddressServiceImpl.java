package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Address;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.repository.AddressRepository;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.management.service.AddressService;
import com.csidigital.shared.dto.request.AddressRequest;
import com.csidigital.shared.dto.response.AddressResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository partnerRepository;

    //Add new address
    @Override
    public AddressResponse createAddress(AddressRequest request) {
        Partner partner = partnerRepository.findById(request.getPartnerNum())
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found"));
        Address address = modelMapper.map(request, Address.class);
        address.setPartner(partner);
        Address AddressSaved = addressRepository.save(address);
        return modelMapper.map(AddressSaved, AddressResponse.class);
    }

    //Get list of all addresses
    @Override
    public List<AddressResponse> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> addressList = new ArrayList<>();

        for (Address address : addresses) {
            AddressResponse response = modelMapper.map(address, AddressResponse.class);
            addressList.add(response);
        }

        return addressList;
    }

    //Get a specific address by its id
    @Override
    public AddressResponse getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address with id " +id+ " not found"));
        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
        return addressResponse;
    }

    //Update a specific address by its id
    @Override
    public AddressResponse updateAddress(AddressRequest request, Long id) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address with id: " + id + " not found"));
        modelMapper.map(request, existingAddress);
        Address savedAddress = addressRepository.save(existingAddress);
        return modelMapper.map(savedAddress, AddressResponse.class);
    }

    //Delete a specific address by its id
    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}


