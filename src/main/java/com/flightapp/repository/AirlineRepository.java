package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.model.AirlineEntity;

public interface AirlineRepository extends ReactiveMongoRepository<AirlineEntity, Integer>{

}
