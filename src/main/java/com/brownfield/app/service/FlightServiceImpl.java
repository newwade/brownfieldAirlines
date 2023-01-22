package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.exception.BadRequestException;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.model.request.FlightSearchRequest;
import com.brownfield.app.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;


    @Override
    public Flight saveFlight(Flight flight) {
        flight.setOrigin(StringUtils.trimAllWhitespace(flight.getOrigin()));
        flight.setDestination(StringUtils.trimAllWhitespace(flight.getDestination()));
        flight.getFlightInfo().setFlightNumber(flight.getFlightInfo().getFlightNumber().toUpperCase());
        if(flight.getOrigin().equals(flight.getDestination())){
            throw new BadRequestException("invalid origin or destination");
        }
        if(flight.getFlightDate().isBefore(LocalDate.now())){
            throw new BadRequestException("invalid flight date");
        }
        Optional<Flight> flight_db = flightRepository.findByFlightDateAndDepartureTimeAndFlightInfoFlightNumber(flight.getFlightDate()
                ,flight.getDepartureTime(),flight.getFlightInfo().getFlightNumber());
        if(flight_db.isPresent()){
            throw new BadRequestException("flight already exists");
        }
        LocalTime arrivalTime = flight.getDepartureTime().plusHours(flight.getFlightDuration().getHour()
        ).plusMinutes(flight.getFlightDuration().getMinute()).plusSeconds(flight.getFlightDuration().getSecond());
        flight.setArrivalTime(arrivalTime);
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
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
        return flightRepository.findByFlightInfoAirlineInfoNameOfAirlineOrderByDepartureTimeAsc(airlineName);
    }

    @Override
    public List<Flight> findFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightInfoFlightNumberOrderByDepartureTimeAsc(flightNumber);
    }

    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByOriginDestinationDateService(FlightSearchRequest request) {
        if(request.getDate().isBefore(LocalDate.now())){
            throw new BadRequestException("invalid flight date");
        }
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDateOrderByDepartureTimeAsc(request.getOrigin(), request.getDestination(), request.getDate());
        List<Flight> searchResults = flights.stream().filter(flight -> {
            return flight.getInventory().getCount()>=request.getPassengers();
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
