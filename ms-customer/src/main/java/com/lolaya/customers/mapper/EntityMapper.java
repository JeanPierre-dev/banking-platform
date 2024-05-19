package com.lolaya.customers.mapper;

public interface EntityMapper<D, E> {
    E toDocument(D model);
    D toModel(E domain);
}
