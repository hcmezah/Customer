package com.p1.users.project1NTTData.controller;

import com.p1.users.project1NTTData.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@WebFluxTest(ControllerCustomer.class)
class ControllerCustomerTest {
    @Autowired
    private WebTestClient webClient;
    @MockBean
    ControllerCustomer service;

    Customer customer;

    @BeforeEach
    void setUp() throws Exception {
        customer=new Customer("1","Hernan","Meza","23415263","Personal","PYME",true);
    }

    @Test
    void searchNroDocument() {
        Mockito.when(service.searchNroDocument(customer.get_id()))
                .thenReturn(Mono.just(customer));
        ((WebTestClient.RequestBodySpec) webClient.get().uri("/customer/1")
                .accept(MediaType.APPLICATION_JSON))
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus()
                .isEqualTo(200);


    }

    @Test
    void TestgetClient() {
        Flux<Customer> customerFlux = Flux.just(customer);
        Mockito.when(service.getClient())
                .thenReturn(customerFlux);

        webClient.get().uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class)
                .getResponseBody();
    }

    @Test
    void delete() {
        Mockito.when(service.delete(customer.get_id()))
                .thenReturn(Mono.just(customer));
        webClient.delete().uri("/customer/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(200);

    }

    @Test
    void Testedit() {
        Mockito.when(service.edit(customer))
                .thenReturn(Mono.just(customer));

        webClient.put().uri("/customer/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus().isEqualTo(200);

    }

    @Test
    void Testcreate() {
        Mockito.when(service.create(customer))
                .thenReturn(Mono.just(customer));

        webClient.post().uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void TestgetById() {
        Mockito.when(service.getById(customer.get_id()))
                .thenReturn(Mono.just(customer));
        ((WebTestClient.RequestBodySpec) webClient.get().uri("/customer/getById/1")
                .accept(MediaType.APPLICATION_JSON))
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus()
                .isEqualTo(200);


    }
}