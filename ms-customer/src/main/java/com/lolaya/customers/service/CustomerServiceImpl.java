package com.lolaya.customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lolaya.customers.dao.CustomerDao;
import com.lolaya.customers.documents.Customer;

import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerDao customerRepository;

    @Override
    public Mono<Customer> save(Mono<Customer> customer) {
        return customer.flatMap(customerRepository::insert);
    }

    @Override
    public Mono<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> update(Long id, Mono<Customer> customer) {
        return customerRepository.findById(id)
                .flatMap(c -> customer)
                .doOnNext(e -> e.setId(id))
                .flatMap(customerRepository::save);
    }

    @Override
    public Mono<Void> delete(Customer customer) {
        return customerRepository.delete(customer);
    }

}
