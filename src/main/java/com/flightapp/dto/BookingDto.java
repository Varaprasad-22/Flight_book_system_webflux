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
}
