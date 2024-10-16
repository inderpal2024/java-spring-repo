package com.gem.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routes(CustomerRepository customerRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/customers")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(customerRepository.findAll(), Customer.class))
                .andRoute(RequestPredicates.GET("/customers/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(customerRepository.findById(request.pathVariable("id")), Customer.class));
    }
}
