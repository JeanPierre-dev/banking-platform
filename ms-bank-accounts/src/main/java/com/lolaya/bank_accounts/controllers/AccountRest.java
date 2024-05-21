/**
 * 
 */
package com.lolaya.bank_accounts.controllers;

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

import com.lolaya.bank_accounts.api.AccountsApi;
import com.lolaya.bank_accounts.mapper.AccountMapper;
import com.lolaya.bank_accounts.model.AccountBankDto;
import com.lolaya.bank_accounts.service.AccountServiceImpl;
import com.lolaya.customers.exceptions.ResponseEntityExceptions;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;


/**
 * 
 */
@Validated
@Tag(name = "Account", description = "Account controller")
@RestController
@RequestMapping("/api/v1")
public class AccountRest implements AccountsApi {
	
	@Autowired
    private AccountServiceImpl accountService;
	
    @Autowired
    private AccountMapper accountMapper;
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ResponseEntity<Map<String, Object>>> addAccount(
			@Valid Mono<AccountBankDto> accountBankDto, 
			ServerWebExchange exchange) {

		Map<String, Object> response = new HashMap<>();
        return accountService.save(accountBankDto.map(accountMapper::toDocument))
                .map(accountMapper::toModel)
                .map(c -> {
                    response.put("account", c);
                    response.put("message", "Bank account saved successfully");
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(WebExchangeBindException.class, ResponseEntityExceptions.getThrowableMonoFunction(response))
                .onErrorResume(DuplicateKeyException.class, ResponseEntityExceptions.getThrowableDuplicate(response));
	}
	
}
