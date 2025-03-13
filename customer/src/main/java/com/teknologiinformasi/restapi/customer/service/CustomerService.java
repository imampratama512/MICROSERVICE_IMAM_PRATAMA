package com.teknologiinformasi.restapi.customer.service;

import java.util.List;
import java.util.Optional;

import com.teknologiinformasi.restapi.customer.model.Customer;
import com.teknologiinformasi.restapi.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

@Service
public class CustomerService {


   @Autowired
   private CustomerRepository customerRepository;


   public List<Customer> getAll() {
       return customerRepository.findAll();
   }


   public Optional<Customer> getById(Long id) {
       return customerRepository.findById(id);
   }


   public Customer createcustomer(Customer customer) {
       return customerRepository.save(customer);
   }


   public Customer updatecustomer(Long id, Customer customerDetails) {
       Customer customer = customerRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("customer not found with id " + id));
       customer.setName(customerDetails.getName());
       customer.setEmail(customerDetails.getEmail());
       customer.setAddress(customerDetails.getAddress());
       return customerRepository.save(customer);
   }


   public void deletecustomer(Long id) {
       Customer customer = customerRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("customer not found with id " + id));
       customerRepository.delete(customer);
   }
}

