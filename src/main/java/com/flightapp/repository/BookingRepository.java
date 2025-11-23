package com.flightapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightapp.model.BookingEntity;

public interface BookingRepository extends MongoRepository<BookingEntity, Integer> {

}
