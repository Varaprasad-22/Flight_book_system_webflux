package com.flightapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightapp.dto.BookingDto;
import com.flightapp.dto.BookingGetResponse;
import com.flightapp.dto.PassengerDto;
import com.flightapp.model.BookingEntity;
import com.flightapp.model.PassengerEntity;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingFlightServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Override
	public Mono<ResponseEntity<String>> bookTicket(@Valid BookingDto bookingData, int flightId) {
		// TODO Auto-generated method stub
		return flightRepo.findByFlightId(flightId)
				.switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
                .flatMap(flight -> {

                    if (flight.getAvaliableSeats() < bookingData.getNoOfSeats()) {
                        return Mono.error(new RuntimeException("Not enough seats"));
                    }

                    String pnr = String.valueOf(10000000 + new Random().nextInt(90000000));

                    BookingEntity booking = new BookingEntity();
                    booking.setPnr(pnr);
                    booking.setFlightId(flightId);
                    booking.setEmail(bookingData.getEmailId());
                    booking.setName(bookingData.getName());
                    booking.setNoOfSeats(bookingData.getNoOfSeats());
                    booking.setPassengers(mapPassengers(bookingData.getPassengers()));
                    booking.setTotalPrice(flight.getPriveOneWay()*bookingData.getNoOfSeats());
                    booking.setBookingTime(LocalDateTime.now());
                    booking.setStatus("BOOKED");

                    flight.setAvaliableSeats(
                            flight.getAvaliableSeats() - bookingData.getNoOfSeats());

                    return bookingRepo.save(booking)
                            .zipWith(flightRepo.save(flight))
                            .map(t -> ResponseEntity
                                    .status(201)
                                    .body(pnr));
                });

	}

	@Override
	public Mono<BookingGetResponse> getBookingDetails(String pnr) {
		// TODO Auto-generated method stub
		 return bookingRepo.findByPnr(pnr)
	                .switchIfEmpty(Mono.error(new RuntimeException("PNR not found")))
	                .map(booking-> new BookingGetResponse(
	                		booking.getFlightId(),
	                		booking.getPnr(),
	                        mapPassengerDto(booking.getPassengers())
	                ));
	}

	@Override
	public Flux<BookingDto> getHistoryByEmail(String email) {
		// TODO Auto-generated method stub
		return bookingRepo.findByEmail(email)
                .map(b -> new BookingDto(
                        b.getEmail(),
                        b.getName(),
                        b.getNoOfSeats(),
                        mapPassengerDto(b.getPassengers()),
                        null,
                        null
                ));
	}

	@Override
	public Mono<Void> cancelBooking(String pnr) {
		// TODO Auto-generated method stub
		 return bookingRepo.findByPnr(pnr)
	                .switchIfEmpty(Mono.error(new RuntimeException("PNR not found")))
	                .flatMap(booking -> {
	                	 LocalDateTime journey = booking.getJourneyDateTime();
	                     LocalDateTime now = LocalDateTime.now();
	                     if (journey.minusHours(24).isBefore(now)) {
	                         return Mono.error(new RuntimeException(
	                             "Cancellation not allowed within 24 hours of journey"));
	                     }
	                     booking.setStatus("CANCELLED");
	                     return bookingRepo.save(booking);
	                })
	                .then();
	}
	
	 private List<PassengerEntity> mapPassengers(List<PassengerDto> dto) {
	        return dto.stream()
	                .map(p -> new PassengerEntity(
	                		  p.getName(),
	                          p.getGender(),
	                          p.getAge(),
	                          p.getSeatNo(),
	                          p.getMeal()
	                ))
	                .toList();
	    }
	 private List<PassengerDto> mapPassengerDto(List<PassengerEntity> entity) {
	        return entity.stream()
	                .map(e -> new PassengerDto(
	                		e.getName(),
	                        e.getGender(),
	                        e.getAge(),
	                        e.getMealPreference(),
	                        e.getSeatNumber()
	                ))
	                .toList();
	 }
}
