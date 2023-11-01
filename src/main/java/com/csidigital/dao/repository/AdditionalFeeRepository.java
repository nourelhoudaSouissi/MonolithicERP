package com.csidigital.dao.repository;

import com.csidigital.dao.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalFeeRepository extends JpaRepository<AdditionalFee,Long> {

    @Query("SELECT COALESCE(SUM(a.priceWithAllTaxIncluded), 0) FROM AdditionalFee a WHERE a.bill = :bill")
    Double calculateTotalAmountByBill(@Param("bill") Bill bill);

    @Query("SELECT COALESCE(SUM(a.priceWithAllTaxIncluded), 0) FROM AdditionalFee a WHERE a.bill = :bill")
    Double calculateTotalWithDiscountByBill(@Param("bill") Bill bill);

    @Query("SELECT COALESCE(SUM(a.discount), 0) FROM AdditionalFee a WHERE a.bill = :bill")
    Double calculateTotalDiscountByBill(@Param("bill") Bill bill);
}
