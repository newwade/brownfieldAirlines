package com.brownfield.app.repository;

import com.brownfield.app.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByOriginAndDestinationAndFlightDateOrderByDepartureTimeAsc(String origin, String destination, LocalDate date);
    List<Flight> findByFlightInfoAirlineInfoNameOfAirlineOrderByDepartureTimeAsc(String airlineName);
    List<Flight> findByFlightInfoFlightNumberOrderByDepartureTimeAsc(String flightNumber);
}
