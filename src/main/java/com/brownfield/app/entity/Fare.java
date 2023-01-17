package com.brownfield.app.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fare")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long fareId;
    @NotBlank(message = "currency cannot be empty")
    private String currency;
    @DecimalMin(value = "1.0", message = "Please Enter a valid Deposit Amount")
    private double fare;
    @OneToOne(mappedBy ="fare")
    @JsonIgnore
    private Flight flight;
}
