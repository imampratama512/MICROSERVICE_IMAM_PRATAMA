package com.teknologiinformasi.restapi.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import com.teknologiinformasi.restapi.customer.model.Customer;
import com.teknologiinformasi.restapi.customer.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {


   @Autowired
   private CustomerService customerService;


   // GET semua customer
   @GetMapping
   public List<Customer> getAllcustomers() {
       return customerService.getAll();
   }


   // GET customer berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Customer> getcustomerById(@PathVariable Long id) {
       return customerService.getById(id)
               .map(customer -> ResponseEntity.ok(customer))
               .orElse(ResponseEntity.notFound().build());
   }


   // POST membuat customer baru
   @PostMapping
   public Customer createcustomer(@RequestBody Customer customer) {
       return customerService.createcustomer(customer);
   }


   // PUT update customer yang ada
   @PutMapping("/{id}")
   public ResponseEntity<Customer> updatecustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
       try {
           Customer updatedcustomer = customerService.updatecustomer(id, customerDetails);
           return ResponseEntity.ok(updatedcustomer);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
   }


   // DELETE customer
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletecustomer(@PathVariable Long id) {
       try {
           customerService.deletecustomer(id);
           return ResponseEntity.ok("customer deleted successfully");
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
   }
}

