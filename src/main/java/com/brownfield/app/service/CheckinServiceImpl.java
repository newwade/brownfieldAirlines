package com.brownfield.app.service;

import com.brownfield.app.entity.Checkin;
import com.brownfield.app.entity.Passenger;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckinServiceImpl implements CheckinService{

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private CheckinRepository checkinRepository;

    @Override
    public Checkin saveChecking(Checkin checkin) {
        Passenger passenger=passengerService.findPassengerById(checkin.getPassenger().getPassengerId());
        checkin.setDateTime(LocalDateTime.now());
        checkin.setPassenger(passenger);
        Checkin checkin_db =  checkinRepository.save(checkin);
        passenger.setCheckin(checkin_db);
        passengerService.savePassenger(passenger);
        return checkin_db;
    }

    @Override
    public Checkin findByCheckinId(long checkinId) {
        Optional<Checkin> checkin = checkinRepository.findById(checkinId);
        if(checkin.isEmpty())
            throw new RecordNotFoundException("No Checkin record found "+checkinId);
        return checkin.get();
    }
}
