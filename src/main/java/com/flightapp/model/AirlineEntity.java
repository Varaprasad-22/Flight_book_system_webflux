package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("airlines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineEntity {

	@Id
	private int id;
	private String airlineName;
}
