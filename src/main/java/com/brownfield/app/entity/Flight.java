package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate flightDate;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime flightTime;
    @OneToOne(cascade = CascadeType.ALL)
    private FlightInfo flightInfo;
    @OneToOne(cascade = CascadeType.ALL)
    private Fare fare;
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;
}
