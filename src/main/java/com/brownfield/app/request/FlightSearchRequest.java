package com.brownfield.app.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightSearchRequest {
    private String origin;
    private String destination;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
}
