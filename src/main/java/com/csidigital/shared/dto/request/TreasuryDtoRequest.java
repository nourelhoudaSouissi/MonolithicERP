package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.CategoryCaisse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TreasuryDtoRequest {

    private Long amount ;
    private Date date;
    private String description ;
    private CategoryCaisse categoryCaisse;

}
