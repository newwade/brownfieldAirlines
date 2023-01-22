package com.brownfield.app.service;

import com.brownfield.app.entity.Fare;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FareServiceImpl implements FareService {

    @Autowired
    private FareRepository fareRepository;

    @Override
    @Transactional
    public Fare saveFare(Fare fare) {
        Fare fare_db =  fareRepository.save(fare);
        return fare_db;
    }

    @Override
    public Fare updateFare(Fare fare) {
        Fare fare_db = findFareByID(fare.getFareId());
        if(fare.getCurrency() != null)  {
            fare_db.setCurrency(fare.getCurrency());
        }
        if(fare.getFare() !=0){
            fare_db.setFare(fare.getFare());
        }
        return fareRepository.save(fare_db);
    }

    @Override
    public Fare findFareByID(long id) {
        Optional<Fare> fare = fareRepository.findById(id);
        if(fare.isEmpty()){
            throw new RecordNotFoundException("Record not found for fare : "+id);
        }
        return fare.get();
    }

    @Override
    public Fare findFareByFlightId(long flightId) {
        return fareRepository.findByFlightId(flightId);
    }

}
