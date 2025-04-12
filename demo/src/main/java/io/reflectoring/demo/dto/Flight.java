package io.reflectoring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Flight {
    private String id;
    private String destination;
    private String source;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
