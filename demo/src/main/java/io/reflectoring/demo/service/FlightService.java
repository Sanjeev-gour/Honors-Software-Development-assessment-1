package io.reflectoring.demo.service;

import io.reflectoring.demo.dto.Flight;
import io.reflectoring.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights(@RequestParam(required = false, defaultValue = "asc", name = "sort") String sort){
        return flightRepository.getAllFlights(sort);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.addFlight(flight);
    }


    public Flight getFlightById(@PathVariable String id){
        System.out.println("service "+id);
        return flightRepository.getFlightById(id);
    }

    public List<Flight> getFlightSchedules(String flightId, List<String> dates){
        return flightRepository.getFlightSchedules(flightId,dates);
    }
}

