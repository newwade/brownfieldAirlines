package com.brownfield.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flightInfo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightInfoId;
    private String flightNumber;
    private String flightType;
    private int numberOfSeats;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flightAirlineInfo", joinColumns = {
            @JoinColumn(name = "flightInfoId", referencedColumnName = "flightInfoId") }, inverseJoinColumns = {
            @JoinColumn(name = "airlineId", referencedColumnName = "airlineId") })
    private AirlineInfo airlineInfo;

}
