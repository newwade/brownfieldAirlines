package com.brownfield.app.service;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.request.BookingRequest;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    BookingRecord saveBooking(BookingRequest bookingRequest);

    BookingRecord findBookingById(long id);

    List<BookingRecord> findAllBooking();

}
