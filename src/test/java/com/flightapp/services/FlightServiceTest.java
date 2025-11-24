package com.flightapp.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightapp.dto.FlightDto;
import com.flightapp.dto.SearchDto;
import com.flightapp.model.FlightEntity;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.FlightServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
	
	@Mock
	private FlightRepository flightRepo;
	@InjectMocks
    private FlightServiceImpl flightService;
    private FlightDto flightDto;
    
    @BeforeEach
    void setUp() {
        flightDto = new FlightDto();
        flightDto.setAirlineName("air India");
        flightDto.setFlightNumber("ABC123");
        flightDto.setFromPlace("Hyd");
        flightDto.setToPlace("Del");
        flightDto.setDepatureTime(LocalDateTime.now().plusDays(1));
        flightDto.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(2));
        flightDto.setTotalSeats(100);
        flightDto.setPrice(5000);
    }
    @Test
    void testAddFlight() {
        when(flightRepo.count()).thenReturn(Mono.just(5L));
        FlightEntity saved = new FlightEntity();
        saved.setFlightId(6);
        when(flightRepo.save(any())).thenReturn(Mono.just(saved));
        StepVerifier.create(flightService.addFlight(flightDto))
                .assertNext(response -> {
                    assertThat(response.getStatusCode().toString()).isEqualTo("201 CREATED");
                    assertThat(response.getBody()).isEqualTo(6);
                })
                .verifyComplete();
    }
    @Test
    void testSearch() {
        SearchDto search = new SearchDto(
                "Hyd", "De", LocalDate.now().plusDays(1), "oneWay", null);

        FlightEntity flight = new FlightEntity();
        flight.setAirlineName("Indigo");
        flight.setFromPlace("Hyd");
        flight.setToPlace("Del");
        flight.setDepatureTime(LocalDateTime.now().plusDays(1));

        when(flightRepo.findByFromPlaceAndToPlaceAndDepatureTimeBetween(
                anyString(), anyString(), any(), any())
        ).thenReturn(Flux.just(flight));

        StepVerifier.create(flightService.search(search))
                .assertNext(result -> {
                    assertThat(result.getOutboundFlights()).hasSize(1);
                    assertThat(result.getInboundFlights()).isEmpty();
                })
                .verifyComplete();
    }


}
