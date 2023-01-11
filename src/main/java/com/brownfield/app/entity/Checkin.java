package com.brownfield.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long checkinId;
    private Integer seatNumber;
    private Integer gateNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
