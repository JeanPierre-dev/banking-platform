package com.lolaya.bank_accounts.service;

import com.lolaya.bank_accounts.documents.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
	
	Mono<Account> save(Mono<Account> account);
	
    Mono<Account> findById(Long id);
    
    Flux<Account> findAll();
    
    Mono<Account> update(Long id, Mono<Account> account);
    
    Mono<Void> delete(Account account);

}
