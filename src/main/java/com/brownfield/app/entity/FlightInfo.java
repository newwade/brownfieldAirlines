package com.brownfield.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "flightnumber cannot be empty")
    private String flightNumber;
    @NotBlank(message = "flighttype cannot be empty")
    private String flightType;
    @NotNull
    private Integer numberOfSeats;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flightAirlineInfo", joinColumns = {
            @JoinColumn(name = "flightInfoId", referencedColumnName = "flightInfoId") }, inverseJoinColumns = {
            @JoinColumn(name = "airlineId", referencedColumnName = "airlineId") })
    private AirlineInfo airlineInfo;

}
