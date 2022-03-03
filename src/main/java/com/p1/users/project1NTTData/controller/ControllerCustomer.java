package com.p1.users.project1NTTData.controller;

import com.p1.users.project1NTTData.model.Customer;
import com.p1.users.project1NTTData.service.ServiceCustomer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ControllerCustomer {

    private final ServiceCustomer service;
    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @GetMapping("/{id}")
    public Mono<Customer> searchNroDocument(@PathVariable("id") String nroDocument){
        return service.finByNroDocumento(nroDocument);
    }

    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @GetMapping
    public Flux<Customer> getClient(){
        return service.findAll();
    }

    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Mono<Customer> delete (@PathVariable String id){
        return service.delete(id);
    }

    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @PutMapping("/update")
    public Mono<Customer> edit(@RequestBody Customer client){
        //buscamos el id para obtener el client
        return service.update(client);

    }

    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody Customer client){
        return service.save(client);
    }

    @CircuitBreaker(name="clients", fallbackMethod = "fallback")
    @TimeLimiter(name="clients")
    @GetMapping("/getById/{id}")
    public Mono<Customer> getById(@PathVariable String id){
        return service.finById(id);
    }



}