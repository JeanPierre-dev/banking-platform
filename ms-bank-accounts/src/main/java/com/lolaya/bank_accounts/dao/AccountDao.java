package com.lolaya.bank_accounts.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.lolaya.bank_accounts.documents.Account;

public interface AccountDao extends ReactiveMongoRepository<Account, Long> {

}
