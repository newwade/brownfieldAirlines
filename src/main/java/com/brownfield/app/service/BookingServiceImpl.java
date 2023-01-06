package com.brownfield.app.service;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.entity.Checkin;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.entity.Passenger;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.BookingRepository;
import com.brownfield.app.model.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightService flightService;

    @Override
    public BookingRecord saveBooking(BookingRequest bookingRequest) {
        BookingRecord bookingRecord = new BookingRecord();
        Flight flight = flightService.findFlightById(bookingRequest.getFlightId());
        bookingRecord.setFlightId(bookingRequest.getFlightId());
        bookingRecord.setPassengers(bookingRequest.getPassengers());
        bookingRecord.setOrigin(flight.getOrigin());
        bookingRecord.setDestination(flight.getDestination());
        bookingRecord.setFlightDate(flight.getFlightDate());
        bookingRecord.setFlightTime(flight.getFlightTime());
        bookingRecord.setFare(flight.getFare().getFare());
        bookingRecord.setSeatNumber((int) ((flight.getFlightInfo().getNumberOfSeats()+1)-flight.getInventory().getCount()));
        bookingRecord.setBookingDate(LocalDateTime.now());
        bookingRecord.setStatus("BOOKED");
        BookingRecord bookingRecordDb = bookingRepository.save(bookingRecord);
        for(Passenger passenger : bookingRecordDb.getPassengers()){
            passenger.setBookingRecord(bookingRecord);
            passengerService.savePassenger(passenger);
        }
        flight.getInventory().setCount(flight.getInventory().getCount()-bookingRecord.getPassengers().size());
        flightService.saveFlight(flight);
        return bookingRecordDb;
    }

    @Override
    public BookingRecord findBookingById(long id) {

        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("No record found for id : "+id);
        }
        return bookingRecord.get();
    }

    @Override
    public List<BookingRecord> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBookingById(long id) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("No record found for id : "+id);
        }
        bookingRepository.deleteById(id);
    }
}
