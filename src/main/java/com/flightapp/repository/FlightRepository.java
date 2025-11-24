package com.flightapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.model.FlightEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveMongoRepository<FlightEntity,String> {

	Mono<FlightEntity> findByFlightId(int flightId);
	
	Flux<FlightEntity> findByFromPlaceAndToPlaceAndDepatureTimeBetween(
	           String fromPlace,
	           String toPlace,
	           LocalDateTime start,
	           LocalDateTime end
	   );
}
