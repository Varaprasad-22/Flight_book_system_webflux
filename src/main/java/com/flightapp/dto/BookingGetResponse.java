package com.flightapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingGetResponse {
	private String flightId;
	private String pnr;
	private List<PassengerDto> passengersList;

}
