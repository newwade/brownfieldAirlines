package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "bookingRecord")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private long flightId;
    private String origin;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime flightTime;
    private double fare;
    private Integer seatNumber;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDate;
    @OneToMany(mappedBy = "bookingRecord",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}
