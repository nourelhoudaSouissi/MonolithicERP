package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.BankAccount;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.repository.BankAccountRepository;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.management.service.PartnerBankAccountService;
import com.csidigital.shared.dto.request.BankAccountRequest;
import com.csidigital.shared.dto.response.BankAccountResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class BankAccountServiceImpl implements PartnerBankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository repository;
    @Override
    public BankAccountResponse createBankAccount(BankAccountRequest request) {
        Partner partner = repository.findById(request.getPartnerNum())
                .orElseThrow(()-> new ResourceNotFoundException("Independent Contact with id " +request.getPartnerNum()+ " not found"));
        BankAccount bankAccount = modelMapper.map(request, BankAccount.class);
        bankAccount.setPartner(partner);
        BankAccount bankAccountSaved = bankAccountRepository.save(bankAccount);
        return modelMapper.map(bankAccountSaved, BankAccountResponse.class);
    }

    @Override
    public List<BankAccountResponse> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountResponse> bankAccountResponseList = new ArrayList<>();

        for (BankAccount bankAccount : bankAccounts) {
            BankAccountResponse response = modelMapper.map(bankAccount, BankAccountResponse.class);
            bankAccountResponseList.add(response);
        }
        return bankAccountResponseList;
    }

    @Override
    public BankAccountResponse getBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bank account with id " +id+ " not found"));
        BankAccountResponse response = modelMapper.map(bankAccount, BankAccountResponse.class);
        return response;
    }

    @Override
    public BankAccountResponse updateBankAccount(BankAccountRequest request, Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bank account with id: " + id + " not found"));
        modelMapper.map(request, bankAccount);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return modelMapper.map(savedBankAccount, BankAccountResponse.class);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
