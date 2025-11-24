package com.flightapp.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("bookings")
public class BookingEntity {

	@Id
	private String bookingId;
	private String pnr;
	private String flightId;
	private String email;
	private String name;
	private LocalDateTime journeyDateTime;
	private boolean roundTrip;
	private List<PassengerEntity> passengers;
	private int noOfSeats;
	private double totalPrice;
	private LocalDateTime bookingTime;
	private String status;
	
	
}
