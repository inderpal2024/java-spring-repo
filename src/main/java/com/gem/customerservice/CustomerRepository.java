package com.gem.customerservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Flux<Customer> findByName(String name);

    Mono<Customer> findByAge(Integer age);
}
