package com.p1.users.project1NTTData.service;

import com.p1.users.project1NTTData.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceCustomer {
    Flux<Customer> findAll();
    Mono<Customer> save(Customer client);
    Mono<Customer> delete(String id);
    Mono<Customer> update(Customer client);
    Mono<Customer> finById(String id);
    Mono<Customer> finByNroDocumento(String numDoc);


}
