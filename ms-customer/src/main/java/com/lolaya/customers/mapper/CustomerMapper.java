package com.lolaya.customers.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.lolaya.customers.documents.Customer;
import com.lolaya.customers.model.CustomerDto;

@Component
public class CustomerMapper implements EntityMapper<CustomerDto, Customer>{
	
    @Override
    public Customer toDocument(CustomerDto model) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(model, customer);
        return customer;
    }

    @Override
    public CustomerDto toModel(Customer document) {
    	CustomerDto customer = new CustomerDto();
        BeanUtils.copyProperties(document, customer);
        return customer;
    }

}
