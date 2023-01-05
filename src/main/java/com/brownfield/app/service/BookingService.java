package com.brownfield.app.service;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.model.request.BookingRequest;

import java.util.List;

public interface BookingService {

    BookingRecord saveBooking(BookingRequest bookingRequest);

    BookingRecord findBookingById(long id);

    List<BookingRecord> findAllBooking();

    void deleteBookingById(long id);

}
