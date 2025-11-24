package com.flightapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
	private String fromPlace;
	private String toPlace;
	private LocalDate depatureDate;
	private String tripType;
	private LocalDate returnDate;
	
}
