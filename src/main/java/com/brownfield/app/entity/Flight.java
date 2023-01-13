package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "flight origin cannot be empty")
    private String origin;
    @NotBlank(message = "flight destination cannot be empty")
    private String destination;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;
    @NotNull
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime departureTime;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime arrivalTime;
    @NotNull
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime flightDuration;
    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flightInfoId")
    private FlightInfo flightInfo;
    @NotNull
    @Valid
    @JoinColumn(name = "fareId")
    @OneToOne(cascade = CascadeType.ALL)
    private Fare fare;
    @NotNull
    @Valid
    @JoinColumn(name = "inventoryId")
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;
}
