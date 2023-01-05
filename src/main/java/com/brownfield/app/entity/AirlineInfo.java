package com.brownfield.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="airlineInfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirlineInfo {

    @Id
    @GeneratedValue
    private long airlineId;
    private String airlineLogo;
    private String nameOfAirline;

}
