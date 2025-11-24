package com.flightapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

	private String emailId;
	private String name;
	private int noOfSeats;
	private List<PassengerDto> passengers;
	private Integer outboundFlightId;
	private Integer returnFlightId;
	public BookingDto(String email, String name, int noOfSeats, List<PassengerDto> passengers,Integer outboundFlightId,
            Integer returnFlightId) {
		// TODO Auto-generated constructor stub
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
