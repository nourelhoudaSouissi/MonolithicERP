package com.csidigital.management.service;

import com.csidigital.dao.entity.Equipment;
import com.csidigital.shared.dto.request.EquipmentRequest;
import com.csidigital.shared.dto.response.EquipmentResponse;

import java.time.LocalDate;
import java.util.List;

public interface EquipmentService {
    EquipmentResponse createEquipment(EquipmentRequest request);
    List<EquipmentResponse> getAllEquipments();
    EquipmentResponse getEquipmentById(Long id);

    EquipmentResponse updateEquipment(EquipmentRequest request, Long id);

    void deleteEquipment(Long id);


    void updateStatusToAvailableById(Long id);
    void updateStatusToUnavailableById(Long id);
    void updateMotifById(Long id, String motifUnavailability, LocalDate disponibilityDate);
    void updateAffectedById(Long id);
    void updateUnaffectedById(Long id);
    void updateRestituationById(Long id, String returnComment, String returnStatus, LocalDate returnDate);
    int countNonAffectables();
    int countAffectables();
    int countNonAmortizables();
    int countAmortizables();
    int countAvailable();
    int countUnavailable();
    int countAffectation();
    int countUnaffectation();
    int countAvailalbeAff();
    int countUnavailalbeAff();




























    List<Equipment> getAffectableEquipments();
    Long countEquipment();


}
