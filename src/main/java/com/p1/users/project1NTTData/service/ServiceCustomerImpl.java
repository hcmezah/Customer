package com.p1.users.project1NTTData.service;

import com.p1.users.project1NTTData.model.Customer;
import com.p1.users.project1NTTData.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ServiceCustomerImpl implements ServiceCustomer {

    private final ICustomerRepository repository;
    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Customer> save(Customer client) {
        client.setStatus(true);
        return repository.save(client);
    }

    @Override
    public Mono<Customer> delete(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setStatus(false);
                    return repository.save(origin);
                });
    }

    @Override
    public Mono<Customer> update(Customer client) {
        return  repository.findById(client.get_id())
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setName(client.getName());
                    origin.setStatus(client.getStatus());
                    origin.setType(client.getType());
                    origin.setLastName(client.getLastName());
                    origin.setNroDocument(client.getNroDocument());
                    origin.setSubType(client.getSubType());
                    return repository.save(origin);
                });
    }

    @Override
    public Mono<Customer> finById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Customer> finByNroDocumento(String numDoc) {
        return repository.findByNroDocument(numDoc);
    }
}