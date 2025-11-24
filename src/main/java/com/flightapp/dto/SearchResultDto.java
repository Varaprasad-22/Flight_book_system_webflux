package com.flightapp.dto;

import java.util.List;



public class SearchResultDto {
	private List<FlightDto> outboundFlights;
	private List<FlightDto> inboundFlights;
	public List<FlightDto> getOutboundFlights() {
		return outboundFlights;
	}
	public void setOutboundFlights(List<FlightDto> outboundFlights) {
		this.outboundFlights = outboundFlights;
	}
	public List<FlightDto> getInboundFlights() {
		return inboundFlights;
	}
	public void setInboundFlights(List<FlightDto> inboundFlights) {
		this.inboundFlights = inboundFlights;
	}

    public SearchResultDto(List<FlightDto> outboundFlights, List<FlightDto> inboundFlights) {
        this.outboundFlights = outboundFlights;
        this.inboundFlights = inboundFlights;
    }


}
