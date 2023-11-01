package com.csidigital.management.service;

import com.csidigital.shared.dto.request.CollectionDtoRequest;
import com.csidigital.shared.dto.response.CollectionDtoResponse;

import java.util.List;

public interface CollectionService {
    //Get All Collection

    List<CollectionDtoResponse> getAllCollections();

    //Get Collection by id

    CollectionDtoResponse getByIdCollection(Long id);

    //Add new Collection

    CollectionDtoResponse CreateCollection(CollectionDtoRequest CollectionDtoRequest);

    //Update Collection by id
    CollectionDtoResponse updateCollection(CollectionDtoRequest request, Long id);
    //delete Collection by id
    void deleteCollectionById(Long id);


}
