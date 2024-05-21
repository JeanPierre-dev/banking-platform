/**
 * 
 */
package com.lolaya.customers.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import com.lolaya.customers.api.CustomerApi;
import com.lolaya.customers.exceptions.ResponseEntityExceptions;
import com.lolaya.customers.mapper.CustomerMapper;
import com.lolaya.customers.model.CustomerDto;
import com.lolaya.customers.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 */
@Validated
@Tag(name = "Customer", description = "Customer controller")
@RestController
@RequestMapping("/api/v1")
public class CustomerRest implements CustomerApi {
	
	@Autowired
    private CustomerService customerService;
	
    @Autowired
    private CustomerMapper customerMapper;
	
    /**
     * {@inheritDoc}
     */
	@Override
    public Mono<ResponseEntity<Map<String, Object>>> addCustomer(@Valid Mono<CustomerDto> customerDto, ServerWebExchange exchange) {
		 Map<String, Object> response = new HashMap<>();
	        return customerService.save(customerDto.map(customerMapper::toDocument))
	                .map(customerMapper::toModel)
	                .map(c -> {
	                    response.put("customer", c);
	                    response.put("message", "Customer saved successfully");
	                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	                })
	                .onErrorResume(WebExchangeBindException.class, ResponseEntityExceptions.getThrowableMonoFunction(response))
	                .onErrorResume(DuplicateKeyException.class, ResponseEntityExceptions.getThrowableDuplicate(response));
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public Mono<ResponseEntity<CustomerDto>> getCustomerById(Long id, ServerWebExchange exchange) {
        System.out.println("GET RECIEVED");
		return customerService.findById(id)
                .map(customerMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public Mono<ResponseEntity<Flux<CustomerDto>>> getAllCustomers(ServerWebExchange exchange) {
		return customerService.findAll()
				.map(customerMapper::toModel)
				.collectList()
				.map(customers -> ResponseEntity.ok().body(Flux.fromIterable(customers)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public Mono<ResponseEntity<Map<String, Object>>> updateCustomer(Long id, Mono<CustomerDto> customer, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();
        return customerService.update(id, customer.map(customerMapper::toDocument))
                .map(customerMapper::toModel)
                .map(c -> {
                    response.put("customer", c);
                    response.put("message", "Customer updated successfully");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .onErrorResume(WebExchangeBindException.class, ResponseEntityExceptions.getThrowableMonoFunction(response))
                .onErrorResume(DuplicateKeyException.class, ResponseEntityExceptions.getThrowableDuplicate(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ResponseEntity<Void>> deleteCustomer(Long id, ServerWebExchange exchange) {
		return customerService.findById(id)
				.flatMap(c -> customerService.delete(c)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
