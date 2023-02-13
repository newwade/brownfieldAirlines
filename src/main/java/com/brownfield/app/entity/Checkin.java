package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long checkinId;
    private Integer gateNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    @NotNull(message = "passenger id cannot be empty")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkin")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Passenger passenger;
}
