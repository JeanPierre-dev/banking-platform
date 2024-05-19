package com.lolaya.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.lolaya.customers.dao.CustomerDao;
import com.lolaya.customers.documents.Customer;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class CustomersServiceApplication implements CommandLineRunner {

	@Autowired
	private CustomerDao customerDao;
	
//	@Autowired
//	private ReactiveMongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(CustomersServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		mongoTemplate.dropCollection("CUSTOMER").subscribe();
		
//		Flux.just(
//				new Customer(10L, "Eva", "Palomino"),
//				new Customer(11L, "Luis", "Olaya")
//				)
//		.flatMap(customer -> customerDao.save(customer))
//		.subscribe(customer -> System.out.println("Insert: " + customer.getId()));
	}

}
