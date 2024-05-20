package com.lolaya.customers.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lolaya.customers.model.CustomerDto.CustomerTypeEnum;
import com.lolaya.customers.model.CustomerDto.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CUSTOMER")
public class Customer {
	
	@Id
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String userName;

	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private CustomerTypeEnum customerType;
	
	@Getter @Setter
	private StatusEnum status;

}
