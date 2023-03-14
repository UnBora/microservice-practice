package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.example.service.request.CustomerRegistrationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customer")
public record CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
    @PostMapping
    public void  registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new customer register {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping("/get-all")
    public List<Customer> getCu(){
        return customerService.getAllCustomer();
    }
    @GetMapping("/get-by-id/{id}")
    public Customer getById(@PathVariable int id){
        return customerService.getById(id);
    }
}
