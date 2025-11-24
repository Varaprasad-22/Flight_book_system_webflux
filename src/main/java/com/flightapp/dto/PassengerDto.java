package com.flightapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class PassengerDto {
	@NotBlank(message = "Passenger name is required")
	private String name;
	@NotBlank(message = "Gender is required")
	private String gender;
	@Min(value = 1, message = "Age must be valid")
	private int age;
	@NotBlank(message = "Meal preference is required")
	private String meal;
	@NotBlank(message = "Seat number is required")
	private String seatNo;
	 public PassengerDto(String name, String gender, int age, String meal, String seatNo) {
	        this.name = name;
	        this.gender = gender;
	        this.age = age;
	        this.meal = meal;
	        this.seatNo = seatNo;
	    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
}
