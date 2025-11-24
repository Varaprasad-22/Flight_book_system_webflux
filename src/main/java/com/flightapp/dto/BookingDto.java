package com.flightapp.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDto {
	@NotBlank(message = "Email is required")
	private String emailId;
	@NotBlank(message = "Passenger name is required")
	private String name;
	@Min(value = 1, message = "At least 1 seat must be booked")
	private int noOfSeats;
	@NotNull(message = "Passengers list cannot be empty")
	private List<PassengerDto> passengers;
	private Integer outboundFlightId;
	private Integer returnFlightId;
	public BookingDto(String email, String name, int noOfSeats, List<PassengerDto> passengers,Integer outboundFlightId,
            Integer returnFlightId) {
		this.emailId = email;
	    this.name = name;
	    this.noOfSeats = noOfSeats;
	    this.passengers = passengers;
	    this.outboundFlightId = outboundFlightId;
	    this.returnFlightId = returnFlightId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public List<PassengerDto> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}
	public Integer getOutboundFlightId() {
		return outboundFlightId;
	}
	public void setOutboundFlightId(Integer outboundFlightId) {
		this.outboundFlightId = outboundFlightId;
	}
	public Integer getReturnFlightId() {
		return returnFlightId;
	}
	public void setReturnFlightId(Integer returnFlightId) {
		this.returnFlightId = returnFlightId;
	}
}
