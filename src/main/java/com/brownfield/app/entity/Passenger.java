package com.brownfield.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Checkin checkin;
}
