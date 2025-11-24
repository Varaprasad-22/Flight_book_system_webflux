package com.flightapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

	private String name;
	private String gender;
	private int age;
	private String meal;
	private String seatNo;
}
