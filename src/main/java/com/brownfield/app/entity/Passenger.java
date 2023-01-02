package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;
    private String firstName;
    private String lastName;
    private String gender;
    private String emailAddress;
    private long mobileNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingId")
    @JsonIgnore
    private BookingRecord bookingRecord;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkinId")
    private Checkin checkin;
}
