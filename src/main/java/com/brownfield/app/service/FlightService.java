package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    Flight saveFlight(Flight flight);

    List<Flight> saveAllFlight(List<Flight> flights);

    Flight findFlightById(long id);

    List<Flight> findAllFlight();

    List<Flight> findByOriginDestinationDateService(String origin, String destination, LocalDate date);

}
