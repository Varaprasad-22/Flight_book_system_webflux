package com.flightapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightapp.dto.FlightDto;
import com.flightapp.dto.SearchDto;
import com.flightapp.dto.SearchResultDto;
import com.flightapp.model.FlightEntity;
import com.flightapp.repository.FlightRepository;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	private FlightRepository flightRepo;
	@Override
	public Mono<ResponseEntity<Integer>> addFlight(@Valid FlightDto flightDto) {
		// TODO Auto-generated method stub
		return flightRepo.count()
				.map(count -> Math.toIntExact(count) + 1)
	            .map(nextId -> {

	                FlightEntity entity = new FlightEntity();
	                entity.setFlightId(nextId);
	                entity.setAirlineName(flightDto.getAirlineName());
	                entity.setFromPlace(flightDto.getFromPlace());
	                entity.setToPlace(flightDto.getToPlace());
	                entity.setDepatureTime(flightDto.getDepatureTime());
	                entity.setArrivalTime(flightDto.getArrivalTime());
	                entity.setPriveOneWay(flightDto.getPrice());
	                entity.setPriceRoundWay(flightDto.getPrice() * 2);
	                entity.setTotalSeats(flightDto.getTotalSeats());
	                entity.setAvaliableSeats(flightDto.getTotalSeats());

	                return entity;
	            })
	            .flatMap(entity -> flightRepo.save(entity))
	            .map(saved -> ResponseEntity
	                    .status(HttpStatus.CREATED)
	                    .body(saved.getFlightId()));
	}

	@Override
	public Flux<SearchResultDto> search(@Valid SearchDto searchData) {
		// TODO Auto-generated method stub
		LocalDateTime start = searchData.getDepatureDate().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        
        Mono<List<FlightEntity>> outBound =flightRepo.findByFromPlaceAndToPlaceAndDepatureTimeBetween(
        		searchData.getFromPlace(),
        		searchData.getToPlace(),
        		start,
        		end
        		).collectList();
        Mono<List<FlightEntity>> inBound=Mono.just(List.of());
        if("roundTrip".equalsIgnoreCase(searchData.getTripType())) {
        	LocalDateTime returnStart=searchData.getReturnDate().atStartOfDay();
        	LocalDateTime returnEnd=returnStart.plusDays(1);
        	inBound=flightRepo.findByFromPlaceAndToPlaceAndDepatureTimeBetween(
        			searchData.getToPlace(),
        			searchData.getFromPlace(),
        			returnStart,
        			returnEnd)
        			.collectList();
        	
        }
		return outBound.zipWith(inBound)
				.map(data->new SearchResultDto(
					 data.getT1().stream().map(this::mapEntitiesToDTOs).toList(),
	                 data.getT2().stream().map(this::mapEntitiesToDTOs).toList()
	            ))
	            .flux();
	}
	private FlightDto mapEntitiesToDTOs(FlightEntity e) {
        FlightDto dto = new FlightDto();
        dto.setAirlineName(e.getAirlineName());
        dto.setFromPlace(e.getFromPlace());
        dto.setToPlace(e.getToPlace());
        dto.setDepatureTime(e.getDepatureTime());
        dto.setArrivalTime(e.getArrivalTime());
        dto.setPrice(e.getPriveOneWay());
        dto.setTotalSeats(e.getTotalSeats());
        return dto;
    }

}
