package com.flightapp.services;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightapp.dto.BookingDto;
import com.flightapp.dto.PassengerDto;
import com.flightapp.model.BookingEntity;
import com.flightapp.model.FlightEntity;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.BookingFlightServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@Mock
    private BookingRepository bookingRepo;
    @Mock
    private FlightRepository flightRepo;
    @InjectMocks
    private BookingFlightServiceImpl bookingService;
    private BookingDto bookingDto;
    private FlightEntity flight;
    
    @BeforeEach
    void setUp() {
    	PassengerDto p = new PassengerDto("Vara", "male", 21, "VEG", "1A");
        bookingDto = new BookingDto(
                 "varaprasad22@gmail.com", "Vara", 1, List.of(p), null, null);
        flight = new FlightEntity();
        flight.setFlightId(10);
        flight.setAvaliableSeats(50);
        flight.setPriveOneWay(5000);
        flight.setDepatureTime(LocalDateTime.now().plusDays(5));
    }
    
    @Test
    void testBookTickets() {
        when(flightRepo.findByFlightId(10)).thenReturn(Mono.just(flight));
        when(bookingRepo.save(any())).thenReturn(Mono.just(new BookingEntity()));
        when(flightRepo.save(any())).thenReturn(Mono.just(flight));

        StepVerifier.create(bookingService.bookTicket(bookingDto, 10))
                .assertNext(response ->
                        assertThat(response.getStatusCode().toString()).isEqualTo("201 CREATED")
                )
                .verifyComplete();
    }
    @Test
    void testGetBookingDetailsByPnr() {
        BookingEntity booking = new BookingEntity();
        booking.setPnr("ABC123");
        booking.setFlightId(10);
        booking.setPassengers(List.of());
        when(bookingRepo.findByPnr("ABC123")).thenReturn(Mono.just(booking));
        StepVerifier.create(bookingService.getBookingDetails("ABC123"))
                .assertNext(response ->
                        assertThat(response.getPnr()).isEqualTo("ABC123")
                )
                .verifyComplete();
    }
    @Test
    void testGetHistoryByEmail() {
        BookingEntity booking = new BookingEntity();
        booking.setEmail("varaprasad22@gmail.com");
        booking.setName("Vara");
        booking.setPassengers(List.of());
        when(bookingRepo.findByEmail("varaprasad22@gmail.com")).thenReturn(Flux.just(booking));

        StepVerifier.create(bookingService.getHistoryByEmail("varaprasad22@gmail.com"))
                .expectNextCount(1)
                .verifyComplete();
    }
    @Test
    void testCancelBooking() {
        BookingEntity booking = new BookingEntity();
        booking.setPnr("ABC123");
        booking.setJourneyDateTime(LocalDateTime.now().plusDays(2));
        booking.setStatus("BOOKED");

        when(bookingRepo.findByPnr("ABC123")).thenReturn(Mono.just(booking));
        when(bookingRepo.save(any())).thenReturn(Mono.just(booking));

        StepVerifier.create(bookingService.cancelBooking("ABC123"))
                .verifyComplete();

        assertThat(booking.getStatus()).isEqualTo("CANCELLED");
    }
    @Test
    void testCancelBookingFail() {
        BookingEntity booking = new BookingEntity();
        booking.setJourneyDateTime(LocalDateTime.now().plusHours(5));

        when(bookingRepo.findByPnr("ABC123")).thenReturn(Mono.just(booking));

        StepVerifier.create(bookingService.cancelBooking("ABC123"))
                .expectErrorMatches(e -> e.getMessage().contains("24 hours"))
                .verify();
    }
    
}
