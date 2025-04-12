package io.reflectoring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Ticket {
    private String id;
    private String passengerName;
    private String email;
    private String flightId;
    private String seatNumber;
}
