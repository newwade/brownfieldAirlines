package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight saveFlight(Flight flight);
    Flight findFlightById(long id);
    List<Flight> findFlightByAirline(String airlineName);
    List<Flight> findFlightByFlightNumber(String flightNumber);
    List<Flight> findByOriginDestinationDateService(String origin, String destination, LocalDate date,Integer passengers);
    void deleteFlightById(long id);
    List<Flight> saveAllFlight(List<Flight> flights);
    List<Flight> findAllFlight();

}
