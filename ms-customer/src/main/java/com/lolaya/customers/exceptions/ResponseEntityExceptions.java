package com.lolaya.customers.exceptions;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResponseEntityExceptions {

	private static final String TIMESTAMP = "timestamp";

	public static Function<Throwable, Mono<? extends ResponseEntity<Map<String, Object>>>> getThrowableMonoFunction(Map<String, Object> response){
		return t -> Mono.just(t).cast(WebExchangeBindException.class)
				.flatMap(e -> Mono.just(e.getFieldErrors()))
				.flatMapMany(Flux::fromIterable)
				.map(fieldError -> "Campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
				.collectList()
				.flatMap(l -> {
					response.put(TIMESTAMP, new Date());
					response.put("status", HttpStatus.BAD_REQUEST.value());
					response.put("errors", l);
					return Mono.just(ResponseEntity.badRequest().body(response));
				});
	}
	public static Function<Throwable, Mono<? extends ResponseEntity<Map<String, Object>>>> getThrowableDuplicate(Map<String, Object> response){
		return t -> Mono.just(t).cast(DuplicateKeyException.class)
				.flatMap(l -> {
					response.put(TIMESTAMP, new Date());
					response.put("status", HttpStatus.BAD_REQUEST.value());
					response.put("errors", l.getMessage());
					return Mono.just(ResponseEntity.badRequest().body(response));
				});
	}

}


