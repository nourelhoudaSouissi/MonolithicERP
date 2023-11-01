package com.csidigital.shared.dto.response;


import com.csidigital.shared.enumeration.CategoryCaisse;
import lombok.Data;

import java.util.Date;


@Data
public class TreasuryDtoResponse {
    private Long id;

    private Long amount;
    private Date date;
    private String description ;
    private CategoryCaisse categoryCaisse;

}
