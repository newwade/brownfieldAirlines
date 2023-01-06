package com.brownfield.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fareId;
    private String currency;
    private double fare;

}
