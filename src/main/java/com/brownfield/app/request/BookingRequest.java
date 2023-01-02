package com.brownfield.app.request;

import com.brownfield.app.entity.Passenger;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
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
