package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotBlank(message = "cardName cannot be empty")
    @Column(nullable = false)
    private String cardholderName;
    @NotBlank(message = "cardNumber cannot be empty")
    @Size(min = 16,max=16,message = "invalid card")
    @Column(nullable = false)
    private String cardNumber;
    @NotNull(message = "card verification value cannot be empty")
    @DecimalMin("100")
    @DecimalMax("999")
    @Column(nullable = false)
    private Integer cvc;
}
