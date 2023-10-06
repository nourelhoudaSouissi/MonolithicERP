package com.csidigital.management.service;

import com.csidigital.shared.dto.request.HolidayRequest;
import com.csidigital.shared.dto.response.HolidayResponse;

import java.util.List;

public interface HolidayService {
    HolidayResponse createHoliday(HolidayRequest request);
    List<HolidayResponse> getAllHolidays();
    HolidayResponse getHolidayById(Long id);

    HolidayResponse updateHoliday(HolidayRequest request, Long id);

    void deleteHoliday(Long id);
}
