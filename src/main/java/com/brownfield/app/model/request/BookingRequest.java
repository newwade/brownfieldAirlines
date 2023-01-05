package com.brownfield.app.model.request;

import com.brownfield.app.entity.Passenger;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {
    @Min(value = 1,message = "invalid flight id")
    private long flightId;
    @NotEmpty(message = "passengers cannot be empty")
    List<Passenger> passengers;

}
