package com.flightapp.service;

import org.springframework.http.ResponseEntity;

import com.flightapp.dto.BookingDto;
import com.flightapp.dto.BookingGetResponse;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {

	Mono<ResponseEntity<String>> bookTicket(@Valid BookingDto bookingData, int flightId);

	Mono<BookingGetResponse> getBookingDetails(String pnr);

	Flux<BookingDto> getHistoryByEmail(String email);

	Mono<Void> cancelBooking(String pnr);

}
