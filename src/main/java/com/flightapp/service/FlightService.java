package com.flightapp.service;

import org.springframework.http.ResponseEntity;

import com.flightapp.dto.FlightDto;
import com.flightapp.dto.SearchDto;
import com.flightapp.dto.SearchResultDto;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

	Mono<ResponseEntity<Integer>> addFlight(@Valid FlightDto flight);

	Flux<SearchResultDto> search(@Valid SearchDto searchData);


}
