package com.flightapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.mockito.Mockito.any;
import static org.mockito.ArgumentMatchers.eq;

import com.flightapp.dto.BookingDto;
import com.flightapp.dto.BookingGetResponse;
import com.flightapp.dto.FlightDto;
import com.flightapp.dto.PassengerDto;
import com.flightapp.dto.SearchDto;
import com.flightapp.dto.SearchResultDto;
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = FlightBookingController.class)
public class FlightBookingControllerTest {
	@Autowired
	private WebTestClient webClient;
	@MockitoBean
	private FlightService flightService;
	@MockitoBean
	private BookingService bookingService;
	private FlightDto flightDto;
	private SearchDto searchDto;
	private BookingDto bookingDto;
	private PassengerDto passenger;

	@BeforeEach
	void setUp() {

		passenger = new PassengerDto("Vara", "male", 21, "Veg", "1A");

		flightDto = new FlightDto();
		flightDto.setAirlineName("Indigo");
		flightDto.setFlightNumber("AB123");
		flightDto.setFromPlace("Hyd");
		flightDto.setToPlace("Del");
		flightDto.setDepatureTime(LocalDateTime.now());
		flightDto.setArrivalTime(LocalDateTime.now().plusHours(2));
		flightDto.setTotalSeats(180);
		flightDto.setPrice(4500);

		searchDto = new SearchDto("Hyd", "Del", LocalDate.now(), "oneWay", null);

		bookingDto = new BookingDto("varaprasad22@gmail.com", 
				"vara", 1, List.of(passenger), null, null);
	}

	@Test
	void testAddFlight() {

		Mockito.when(flightService.addFlight(any())).thenReturn(Mono.just(ResponseEntity.status(201).body(10)));

		webClient.post().uri("/api/v1.0/flight/airline/inventory/add")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(flightDto).exchange().expectStatus().isCreated().expectBody(Integer.class).isEqualTo(10);
	}

	@Test
	void testSearchFlights() {

		SearchResultDto result = new SearchResultDto(List.of(flightDto), List.of());

		Mockito.when(flightService.search(any())).thenReturn(Flux.just(result));

		webClient.post().uri("/api/v1.0/flight/search")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(searchDto).exchange().expectStatus().isOk().expectBodyList(SearchResultDto.class).hasSize(1);
	}

	@Test
	void testFlightBooking() {

		Mockito.when(bookingService.bookTicket(any(), eq(5)))
				.thenReturn(Mono.just(ResponseEntity.status(201).body("PNR12345")));

		webClient.post().uri("/api/v1.0/flight/booking/5").contentType(MediaType.APPLICATION_JSON).bodyValue(bookingDto)
				.exchange().expectStatus().isCreated().expectBody(String.class).isEqualTo("PNR12345");
	}

	@Test
	void testGetTicket() {

		BookingGetResponse resp = new BookingGetResponse(5, "PNR12345", List.of(passenger));

		Mockito.when(bookingService.getBookingDetails("PNR12345")).thenReturn(Mono.just(resp));

		webClient.get().uri("/api/v1.0/flight/ticket/PNR12345").exchange().expectStatus().isOk()
				.expectBody(BookingGetResponse.class).value(r -> r.getPnr().equals("PNR12345"));
	}

	@Test
	void testBookingHistory() {

		Mockito.when(bookingService.getHistoryByEmail("john@gmail.com")).thenReturn(Flux.just(bookingDto));

		webClient.get().uri("/api/v1.0/flight/booking/history/john@gmail.com").exchange().expectStatus().isOk()
				.expectBodyList(BookingDto.class).hasSize(1);
	}

	@Test
	void testCancelBooking() {

		Mockito.when(bookingService.cancelBooking("PNR12345")).thenReturn(Mono.empty());

		webClient.delete().uri("/api/v1.0/flight/booking/cancel/PNR12345").exchange().expectStatus().isNoContent();
	}
	
	@Test
    void testGetTicketNotFound() {
		Mockito.when(bookingService.getBookingDetails("X"))
        .thenReturn(Mono.error(new RuntimeException("PNR not found")));
		
		webClient.get().uri("/api/v1.0/flight/ticket/X").exchange()
		.expectStatus().isNotFound();
	}
}
