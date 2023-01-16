package com.brownfield.app.controller;


import com.brownfield.app.entity.Fare;
import com.brownfield.app.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fare")
public class FareController {
    @Autowired
    private FareService fareService;

    @GetMapping
    public ResponseEntity<Fare> findFareById(@RequestParam long id) {
        Fare response = fareService.findFareByID(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/flight/")
    public ResponseEntity<Fare> findFareByFlightId(@RequestParam long flightId) {
        Fare response = fareService.findFareByFlightId(flightId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Fare> saveFare(@RequestBody Fare fareRequest){
        Fare response = fareService.saveFare(fareRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Fare> updateFare(@RequestBody Fare fare){
        Fare response = fareService.updateFare(fare);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteFare(@RequestParam long id){
        fareService.deleteFareById(id);
        return new ResponseEntity("Entity Deleted", HttpStatus.OK);
    }
}
