package com.flightapp.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Flights")
@Data
public class FlightEntity {

	@Id
	private String id;
	private int flightId;
	private String airlineName;
	private String fromPlace;
	private String toPlace;
	private LocalDateTime depatureTime;
	private LocalDateTime arrivalTime;
	
	private double priveOneWay;
	private double priceRoundWay;
	private int totalSeats;
	private int avaliableSeats;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
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
	public LocalDateTime getDepatureTime() {
		return depatureTime;
	}
	public void setDepatureTime(LocalDateTime depatureTime) {
		this.depatureTime = depatureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getPriveOneWay() {
		return priveOneWay;
	}
	public void setPriveOneWay(double priveOneWay) {
		this.priveOneWay = priveOneWay;
	}
	public double getPriceRoundWay() {
		return priceRoundWay;
	}
	public void setPriceRoundWay(double priceRoundWay) {
		this.priceRoundWay = priceRoundWay;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvaliableSeats() {
		return avaliableSeats;
	}
	public void setAvaliableSeats(int avaliableSeats) {
		this.avaliableSeats = avaliableSeats;
	}
}
