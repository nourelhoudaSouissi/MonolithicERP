package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
   private  Long Id ;
   private int num;
   private String street ;
   private Long postalCode;
   private String city ;
   private String region;
   private String country ;
   private String type;
   private Long partnerId;
}
