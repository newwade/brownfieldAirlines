package com.brownfield.app.controller;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.request.FlightSearchRequest;
import com.brownfield.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") long id){
        Flight response = flightService.findFlightById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight){
        Flight response = flightService.saveFlight(flight);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/find")
    public ResponseEntity<List<Flight>> findFlightByOriginDestinationDate(@RequestBody FlightSearchRequest request){
        List<Flight> response = flightService.findByOriginDestinationDateService(request.getOrigin()
                ,request.getDestination(),request.getDate());
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
