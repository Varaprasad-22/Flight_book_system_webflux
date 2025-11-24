package com.flightapp.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlightDto {
	@NotBlank(message = "Airline name is required")
	private String airlineName;
	@NotBlank(message = "Flight number is required")
	private String flightNumber;
	@NotBlank(message = "From place is required")
	private String fromPlace;
	@NotBlank(message = "To place is required")
	private String toPlace;
	@NotNull(message = "Arrival time is required")
	private LocalDateTime arrivalTime;
	@NotNull(message = "Departure time is required")
	private LocalDateTime depatureTime;
	@Min(value = 1, message = "Total seats must be at least 1")
	private int totalSeats;
	@Min(value = 1, message = "Price must be greater than 0")
	private double price;

}
