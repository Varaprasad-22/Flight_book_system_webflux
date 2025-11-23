package com.flightapp.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {

	@Id
	private String flightId;
	private String airlineName;
	private String fromPlace;
	private String toPlace;
	private LocalDateTime depatureTime;
	private LocalDateTime arrivalTime;
	
	private double priveOneWay;
	private double priceRoundWay;
	private int totalSeats;
	private int avaliableSeats;
}
