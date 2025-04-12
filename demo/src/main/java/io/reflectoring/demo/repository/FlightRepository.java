package io.reflectoring.demo.repository;

import io.reflectoring.demo.dto.Flight;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FlightRepository {
    Map<String, Flight> flightsTable;

    @PostConstruct
    public void init(){
        flightsTable = new HashMap<>();
        Flight fl = new Flight("flight1","nagpur","delhi", LocalDateTime.parse("2025-03-20T10:00:00"),LocalDateTime.parse("2025-03-20T10:00:00"));
        flightsTable.put("1",fl);
    }

    public List<Flight> getAllFlights(@RequestParam(required = false, defaultValue = "asc", name = "sort") String sort){

        List<Flight> flights = new ArrayList<>(flightsTable.values());

        if ("desc".equalsIgnoreCase(sort)) {
            flights.sort(Comparator.comparing(Flight::getDepartureTime).reversed());
        } else {
            flights.sort(Comparator.comparing(Flight::getDepartureTime));
        }
        return flights;
    }

    public Flight addFlight(Flight flight) {
        if (flightsTable.containsKey(flight.getId())) {
            return null; // Indicates duplicate flight
        }
        flightsTable.put(flight.getId(), flight);
        return flight;
    }


    public Flight getFlightById(String id) {
        System.out.println("repo"+id);
        System.out.println(this);
        return this.flightsTable.getOrDefault(id, null);
    }

    public List<Flight> getFlightSchedules(String flightId, List<String> dates) {
        Flight flight = flightsTable.get(flightId);

        if (flight == null) {
            return new ArrayList<>();
        }

        // Convert string dates to LocalDate
        List<LocalDate> requestedDates = dates != null ?
                dates.stream().map(LocalDate::parse).collect(Collectors.toList()) :
                new ArrayList<>();


        return requestedDates.isEmpty() ?
                List.of(flight) :
                requestedDates.stream()
                        .map(date -> flight.toBuilder().departureTime(date.atTime(flight.getDepartureTime().toLocalTime())).build())
                        .collect(Collectors.toList());
    }
}

