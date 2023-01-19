package com.brownfield.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.brownfield.app.constant.GenericConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long passengerId;
    @NotBlank(message = "firstname cannot be empty")
    private String firstName;
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;
    @NotBlank(message = "gender cannot be empty")
    private String gender;
    @NotBlank(message = "email cannot be empty")
    @Email(message = "invalid email",regexp = GenericConstant.EMAILREGEXP)
    private String emailAddress;
    @NotBlank(message = "phone cannot be empty")
    @Pattern(regexp = GenericConstant.PHONEREGEXP,message = "invalid phone")
    private String mobileNumber;
    private long seatNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingId")
    @JsonIgnore
    private BookingRecord bookingRecord;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "checkinId")
    @JsonIgnore
    private Checkin checkin;
}
