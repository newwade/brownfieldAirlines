package com.brownfield.app.controller;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.model.request.FlightSearchRequest;
import com.brownfield.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Flight> findFlightById(@PathVariable("id") long id){

        Flight response = flightService.findFlightById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight){
        Flight response = flightService.saveFlight(flight);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity<List<Flight>> findFlightByOriginDestinationDate(@RequestBody FlightSearchRequest request){
        List<Flight> response = flightService.findByOriginDestinationDateService(request.getOrigin()
                ,request.getDestination(),request.getDate());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/model/find")
    public String findFlightModel(@ModelAttribute("flight") FlightSearchRequest request, BindingResult result, Model model){
        Object error = result.getAllErrors();
        List<Flight> response = flightService.findByOriginDestinationDateService(request.getOrigin()
                ,request.getDestination(),request.getDate());
        model.addAttribute("flights",response);
        return "flights";
    }

}
