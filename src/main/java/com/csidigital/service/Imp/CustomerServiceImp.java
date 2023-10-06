package com.csidigital.service.Imp;


import com.csidigital.models.Customer;
import com.csidigital.repository.CustomerRepository;
import com.csidigital.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer CreateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


}
