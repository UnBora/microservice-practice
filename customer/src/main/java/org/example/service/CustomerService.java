package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.request.CustomerRegistrationRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();
//        todo: check if email valid
//        todo: check if email not token
        customerRepository.saveAndFlush(customer);
//        todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

//        todo: send notification
    }

    public @NonNull List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getById(Integer integer) {
        return customerRepository.findById(integer).orElse(null);
    }
}
