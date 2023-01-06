package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
