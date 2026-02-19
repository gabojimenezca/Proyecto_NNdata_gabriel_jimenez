package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        Customer existing = findById(id);
        existing.setName(customer.getName());
        existing.setGender(customer.getGender());
        existing.setIdentification(customer.getIdentification());
        existing.setAddress(customer.getAddress());
        existing.setPhone(customer.getPhone());
        existing.setPassword(customer.getPassword());
        existing.setStatus(customer.getStatus());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
