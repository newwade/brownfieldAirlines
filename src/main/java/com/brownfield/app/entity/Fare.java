package com.brownfield.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fareId;
    @NotBlank
    private String currency;
    @NotNull
    private double fare;

}
