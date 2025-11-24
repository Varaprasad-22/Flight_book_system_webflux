package com.flightapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookingGetResponse {
	private int flightId;
	private String pnr;
	private List<PassengerDto> passengersList;
	public BookingGetResponse(int flightId, String pnr, List<PassengerDto> passengersList) {
		// TODO Auto-generated constructor stub
		this.flightId = flightId;
	    this.pnr = pnr;
	    this.passengersList = passengersList;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public List<PassengerDto> getPassengersList() {
		return passengersList;
	}
	public void setPassengersList(List<PassengerDto> passengersList) {
		this.passengersList = passengersList;
	}

}
