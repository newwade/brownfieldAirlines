package com.brownfield.app.service;

import com.brownfield.app.entity.*;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.BookingRepository;
import com.brownfield.app.model.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public BookingRecord saveBooking(BookingRequest bookingRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
        BookingRecord bookingRecord = new BookingRecord();
        Flight flight = flightService.findFlightById(bookingRequest.getFlightId());
        User user = userService.findById(bookingRequest.getUserId());
        bookingRecord.setFlightId(bookingRequest.getFlightId());
        long pnr = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        bookingRecord.setPnrNumber(pnr);
        bookingRecord.setUser(user);
        bookingRecord.setPassengers(bookingRequest.getPassengers());
        bookingRecord.setOrigin(flight.getOrigin());
        bookingRecord.setDestination(flight.getDestination());
        bookingRecord.setFlightDate(flight.getFlightDate());
        bookingRecord.setDepartureTime(flight.getDepartureTime());
        bookingRecord.setArrivalTime(flight.getArrivalTime());
        bookingRecord.setFare(flight.getFare().getFare()*bookingRequest.getPassengers().size());
        bookingRecord.setBookingDate(LocalDateTime.now());
        bookingRecord.setPayment(bookingRequest.getPayment());
        BookingRecord bookingRecordDb = bookingRepository.save(bookingRecord);
        for(Passenger passenger : bookingRecordDb.getPassengers()){
            passenger.setBookingRecord(bookingRecord);
            passengerService.savePassenger(passenger);
        }
        flight.getInventory().setCount(flight.getInventory().getCount()-bookingRecord.getPassengers().size());
        flightService.updateFlight(flight);
        return bookingRecordDb;
    }

    @Override
    public BookingRecord findBookingById(long id) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("Record not found for booking : "+id);
        }
        return bookingRecord.get();
    }

    @Override
    public List<BookingRecord> findAllBookingByUser(long userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
        User user = userService.findById(userId);
        List<BookingRecord> bookingRecords = bookingRepository.findByUserId(user.getId());
        return bookingRecords;
    }

    @Override
    public List<BookingRecord> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBookingById(long id) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("Record not found for booking : "+id);
        }
        Flight flight = flightService.findFlightById(bookingRecord.get().getFlightId());
        bookingRepository.deleteById(id);
        flight.getInventory().setCount(flight.getInventory().getCount()+bookingRecord.get().getPassengers().size());
        flightService.updateFlight(flight);
    }
}
