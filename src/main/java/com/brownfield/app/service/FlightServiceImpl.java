package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
            throw new RecordNotFoundException("No record found for id : "+id);
        }
        return flight.get();
    }

    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByOriginDestinationDateService(String origin, String destination, LocalDate date) {
        return flightRepository.findByOriginAndDestinationAndFlightDateOrderByFlightTimeAsc(origin,destination,date);
    }

}
