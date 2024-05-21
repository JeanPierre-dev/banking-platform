package com.lolaya.bank_accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lolaya.bank_accounts.dao.AccountDao;
import com.lolaya.bank_accounts.documents.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
    private AccountDao accountRepository;

    @Override
    public Mono<Account> save(Mono<Account> account) {
        return account.flatMap(accountRepository::insert);
    }

    @Override
    public Mono<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
    
    @Override
	public Flux<Account> findAll() {
    	return accountRepository.findAll();
	}

    @Override
    public Mono<Account> update(Long id, Mono<Account> account) {
        return accountRepository.findById(id)
                .flatMap(c -> account)
                .doOnNext(e -> e.setId(id))
                .flatMap(accountRepository::save);
    }

    @Override
    public Mono<Void> delete(Account account) {
        return accountRepository.delete(account);
    }

}
