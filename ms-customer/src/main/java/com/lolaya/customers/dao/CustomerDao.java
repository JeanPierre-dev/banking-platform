package com.lolaya.customers.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.lolaya.customers.documents.Customer;

public interface CustomerDao extends ReactiveMongoRepository<Customer, Long> {

}
