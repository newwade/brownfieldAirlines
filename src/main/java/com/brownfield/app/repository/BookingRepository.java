package com.brownfield.app.repository;

import com.brownfield.app.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingRecord,Long> {

    List<BookingRecord>  findByUserId(Long userid);

}

