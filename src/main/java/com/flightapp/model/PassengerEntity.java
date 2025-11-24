package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerEntity {

	@Id
	private int id;
	private String name;
	private String age;
	private String seatNumber;
	private String mealPreference;
}
