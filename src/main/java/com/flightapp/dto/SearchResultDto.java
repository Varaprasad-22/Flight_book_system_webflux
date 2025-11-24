package com.flightapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDto {
	private List<FlightDto> outboundFlights;
	private List<FlightDto> inboundFlights;

}
