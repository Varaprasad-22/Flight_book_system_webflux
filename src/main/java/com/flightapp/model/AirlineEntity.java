package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("airlines")
public class AirlineEntity {

	@Id
	private String id;
	private String airlineName;
}
