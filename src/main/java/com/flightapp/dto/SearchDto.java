package com.flightapp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class SearchDto {
	@NotBlank(message = "From place is required")
	private String fromPlace;
	@NotBlank(message = "To place is required")
	private String toPlace;
	@NotNull(message = "Departure date is required")
	private LocalDate depatureDate;
	@NotBlank(message = "Trip type is required (oneWay / roundTrip)")
	private String tripType;
	private LocalDate returnDate;
	public SearchDto(String string, String string2, LocalDate now, String string3, LocalDate depature) {
		// TODO Auto-generated constructor stub
		this.fromPlace=string;
		this.toPlace=string2;
		this.depatureDate=now;
		this.tripType=string3;
		this.returnDate=depature;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public LocalDate getDepatureDate() {
		return depatureDate;
	}
	public void setDepatureDate(LocalDate depatureDate) {
		this.depatureDate = depatureDate;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
}
