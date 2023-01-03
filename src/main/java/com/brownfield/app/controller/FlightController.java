package com.brownfield.app.controller;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.request.FlightSearchRequest;
import com.brownfield.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        String password = (String) auth.getCredentials();
        Collection<? extends GrantedAuthority> role = auth.getAuthorities();
        List<String> roles = role.stream().map(elm->elm.toString()).collect(Collectors.toList());
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