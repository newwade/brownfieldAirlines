package com.brownfield.app.service;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> saveAllFlight(List<Flight> flights) {
        return flightRepository.saveAll(flights);
    }

    @Override
    public Flight findFlightById(long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()){
            throw new RecordNotFoundException("Record not found for flight : "+id);
        }
        return flight.get();
    }

    @Override
    public List<Flight> findFlightByAirline(String airlineName) {
        return flightRepository.findByFlightInfoAirlineInfoNameOfAirlineOrderByFlightTimeAsc(airlineName);
    }

    @Override
    public List<Flight> findFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightInfoFlightNumberOrderByFlightTimeAsc(flightNumber);
    }

    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByOriginDestinationDateService(String origin, String destination, LocalDate date) {
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDateOrderByFlightTimeAsc(origin,destination,date);
        List<Flight> searchResults = flights.stream().filter(flight -> {
            return flight.getInventory().getCount()>0;
        }).collect(Collectors.toList());
        return searchResults;
    }

    @Override
    public void deleteFlightById(long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()){
            throw new RecordNotFoundException("Record not found for flight : "+id);
        }
        flightRepository.deleteById(id);
    }

}
