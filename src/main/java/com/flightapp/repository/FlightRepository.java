package com.flightapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.model.FlightEntity;

public interface FlightRepository extends ReactiveMongoRepository<FlightEntity,String> {

}
