package com.brownfield.app.service;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.entity.Passenger;
import com.brownfield.app.repository.BookingRepository;
import com.brownfield.app.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        bookingRecord.setDate(flight.getDate());
        bookingRecord.setTime(flight.getTime());
        bookingRecord.setFare(flight.getFare().getFare());
        bookingRecord.setFlightNumber(flight.getFlightInfo().getFlightNumber());
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
            throw new IllegalArgumentException();
        }
        return bookingRecord.get();
    }

    @Override
    public List<BookingRecord> findAllBooking() {
        return bookingRepository.findAll();
    }
}
