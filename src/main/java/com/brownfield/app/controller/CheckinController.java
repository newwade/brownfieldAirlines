package com.brownfield.app.controller;

import com.brownfield.app.entity.Passenger;
import com.brownfield.app.service.BookingService;
import com.brownfield.app.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/checkin")
public class CheckinController {

    @Autowired
    private PassengerService passengerService;

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> passengerCheckIn(@PathVariable("id") long passengerId){
        Passenger response = passengerService.updateCheckIn(passengerId);
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
