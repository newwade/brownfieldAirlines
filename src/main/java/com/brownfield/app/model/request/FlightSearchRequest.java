package com.brownfield.app.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightSearchRequest {
    @NotEmpty(message = "origin cannot be empty")
    private String origin;
    @NotEmpty(message = "destination cannot be empty")
    private String destination;
    @NotNull(message = "travel date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "number of passengers cannot be empty")
    @Min(value = 1,message = "invalid number of passengers")
    @Max(value = 10,message = "invalid number of passengers")
    private Integer Passengers;
}
