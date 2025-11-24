package com.flightapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.model.BookingEntity;

public interface BookingRepository extends ReactiveMongoRepository<BookingEntity, String> {

}
