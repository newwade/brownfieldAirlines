package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String origin;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime flightTime;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "flightInfoId")
    private FlightInfo flightInfo;
    @JoinColumn(name = "fareId")
    @OneToOne(cascade = CascadeType.ALL)
    private Fare fare;
    @JoinColumn(name = "inventoryId")
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;
}
