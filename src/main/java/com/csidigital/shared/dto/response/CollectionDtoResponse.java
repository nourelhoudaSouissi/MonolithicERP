package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.CategoryCollection;
import com.csidigital.shared.enumeration.TreasuryType;
import lombok.Data;

import java.util.Date;

@Data
public class CollectionDtoResponse {
    private Long id;
    private String category ;
    private Date date ;
    private String description ;
    private TreasuryType treasuryType;
    private BillClientDtoResponse billClient;
    private CategoryCollection CategoryCollection ;



}
