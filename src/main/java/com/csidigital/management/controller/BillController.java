package com.csidigital.management.controller;

import com.csidigital.management.service.BillService;
import com.csidigital.shared.dto.request.BillDtoRequest;
import com.csidigital.shared.dto.response.BillDtoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fm/bills")
@CrossOrigin("*")
public class BillController {

    private final BillService billService;
    @Autowired
    public BillController(BillService billService, ModelMapper modelMapper) {
        this.billService = billService;
    }


    @GetMapping()
    public List<BillDtoResponse> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public BillDtoResponse getBillById(@PathVariable Long id){
        return billService.getBillById(id);
    }

    @PostMapping()
    public BillDtoResponse createBill( @RequestBody BillDtoRequest billDtoRequest){
        return billService.CreateBill(billDtoRequest);
    }

    @PutMapping("/{id}")
    public BillDtoResponse updateBill( @RequestBody  BillDtoRequest billDtoRequest, @PathVariable Long id){
        return billService.updateBill(billDtoRequest, id);
    }


    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id){
        billService.deleteBillById(id);
    }


}

