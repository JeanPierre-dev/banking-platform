package com.lolaya.bank_accounts.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.lolaya.bank_accounts.documents.Account;
import com.lolaya.bank_accounts.model.AccountBankDto;
import com.lolaya.customers.mapper.EntityMapper;


@Component
public class AccountMapper implements EntityMapper<AccountBankDto, Account>{
	
    @Override
    public Account toDocument(AccountBankDto model) {
        Account account = new Account();
        BeanUtils.copyProperties(model, account);
        return account;
    }

    @Override
    public AccountBankDto toModel(Account document) {
    	AccountBankDto account = new AccountBankDto();
        BeanUtils.copyProperties(document, account);
        return account;
    }

}
