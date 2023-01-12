package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long pnrNumber;
    private String origin;
    private String destination;
    private double fare;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime departureTime;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime arrivalTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDate;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment payment;
    @OneToMany(mappedBy = "bookingRecord",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Passenger> passengers;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

}
