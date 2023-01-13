package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
    @NotBlank(message = "airline logo cannot be empty")
    private String airlineLogo;
    @NotBlank(message = "airline name cannot be empty")
    private String nameOfAirline;

}
