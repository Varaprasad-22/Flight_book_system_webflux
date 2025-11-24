package com.flightapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookingExceptions extends RuntimeException{
	
	public BookingExceptions(String message) {
		super(message);
	}
}
