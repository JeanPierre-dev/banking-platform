package com.lolaya.bank_accounts.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lolaya.bank_accounts.model.ConditionsDto;
import com.lolaya.bank_accounts.model.AccountBankDto.AccountTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BANK_ACCOUNTS")
public class Account {
	
	@Id
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private Double amount;

	@Getter @Setter
	private Long customerId;
	
	@Getter @Setter
	private AccountTypeEnum accountType;

	@Getter @Setter
	private List<Integer> holdersId = null;

	@Getter @Setter
	private List<Integer> signatoriesId = null;

	@Getter @Setter
	private ConditionsDto conditions;
	
}
