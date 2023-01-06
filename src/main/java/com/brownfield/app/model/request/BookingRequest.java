package com.brownfield.app.model.request;

import com.brownfield.app.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
