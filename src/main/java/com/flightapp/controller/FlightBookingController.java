package com.flightapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.BookingDto;
import com.flightapp.dto.BookingGetResponse;
import com.flightapp.dto.FlightDto;
import com.flightapp.dto.SearchDto;
import com.flightapp.dto.SearchResultDto;
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightBookingController {

	 private final FlightService flightService;
	 private final BookingService bookingService;

	 public FlightBookingController(FlightService flightService, BookingService bookingService) {
	    this.flightService = flightService;
	    this.bookingService = bookingService;
	 }
	
	@PostMapping("/airline/inventory/add")
	public Mono<ResponseEntity<Integer>> addFlight(@RequestBody @Valid FlightDto flight){
		return flightService.addFlight(flight);
	}
	
	@PostMapping("/search")
	public Flux<SearchResultDto> searchFlight(@Valid @RequestBody SearchDto searchData){
		return flightService.search(searchData);
	}
	
	@PostMapping("/booking/{flightId}")
	public Mono<ResponseEntity<String>> flightBooking(@Valid @RequestBody BookingDto bookingData,@PathVariable int flightId){
		return bookingService.bookTicket(bookingData,flightId);
	}
	@GetMapping("/ticket/{pnr}")
	public Mono<ResponseEntity<BookingGetResponse>> bookingDetails(@PathVariable String pnr) {
		return bookingService.getBookingDetails(pnr)
	            .map(ResponseEntity::ok)
	            .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
	}
	
	@GetMapping("/booking/history/{email}")
    public Flux<BookingDto> getHistoryByEmail(@PathVariable("email") String email) {
        return bookingService.getHistoryByEmail(email);
    }
	
	@DeleteMapping("/booking/cancel/{pnr}")
    public Mono<ResponseEntity<Void>> cancelBooking(@PathVariable String pnr) {
        return bookingService.cancelBooking(pnr)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
