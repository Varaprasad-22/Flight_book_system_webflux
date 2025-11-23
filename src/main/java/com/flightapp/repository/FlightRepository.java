package com.flightapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightapp.model.FlightEntity;

public interface FlightRepository extends MongoRepository<FlightEntity,Integer> {

}
