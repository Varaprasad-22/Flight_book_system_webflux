package com.flightapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.ResponseEntity;

import com.flightapp.model.BookingEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveMongoRepository<BookingEntity, String> {

	 Mono<BookingEntity> findByPnr(String pnr);

	 Flux<BookingEntity> findByEmail(String email);

}
