package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    @Column(nullable = false)
    private String cardholderName;
    @Column(nullable = false)
    private Integer cardNumber;
    @Column(nullable = false)
    private Integer expiryYear;
    @Column(nullable = false)
    private Integer expiryMonth;
    @Column(nullable = false)
    private Integer cvc;
}
