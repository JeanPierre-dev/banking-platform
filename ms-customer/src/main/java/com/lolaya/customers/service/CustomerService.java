package com.lolaya.customers.service;

import com.lolaya.customers.documents.Customer;

import reactor.core.publisher.Mono;

public interface CustomerService {
	
	Mono<Customer> save(Mono<Customer> customer);
	
    Mono<Customer> findById(Long id);
    
    Mono<Customer> update(Long id, Mono<Customer> customer);
    
    Mono<Void> delete(Customer customer);

}
