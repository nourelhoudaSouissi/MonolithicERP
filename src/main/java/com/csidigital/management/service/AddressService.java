package com.csidigital.management.service;

import com.csidigital.shared.dto.request.AddressRequest;
import com.csidigital.shared.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(AddressRequest request);
    List<AddressResponse> getAllAddresses();
    AddressResponse getAddressById(Long id);

    AddressResponse updateAddress(AddressRequest request, Long id);

    void deleteAddress(Long id);
}
