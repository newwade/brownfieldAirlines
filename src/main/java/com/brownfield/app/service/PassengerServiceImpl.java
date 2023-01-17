package com.brownfield.app.service;

import com.brownfield.app.entity.Checkin;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.entity.Passenger;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if(passenger.isEmpty()){
            throw new RecordNotFoundException("No record found for passenger : "+id);
        }
        return passenger.get();
    }

}
