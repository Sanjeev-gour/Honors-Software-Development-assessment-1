package io.reflectoring.demo.controller;

import io.reflectoring.demo.dto.Flight;
import io.reflectoring.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    List<Flight> getAllFlights(@RequestParam(required = false, defaultValue = "asc", name = "sort") String sort){
        return flightService.getAllFlights(sort);
    }

    @PostMapping
    public ResponseEntity<String> createFlight(@RequestBody Flight flight) {
        if (flightService.getFlightById(flight.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Flight with ID " + flight.getId() + " already exists!");
        }

        Flight newFlight = flightService.addFlight(flight);
        return newFlight != null
                ? ResponseEntity.status(HttpStatus.CREATED).body("Flight added successfully with ID: " + newFlight.getId())
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding flight.");
    }

    @GetMapping("/{id}")
    ResponseEntity<Flight> getFlightById(@PathVariable String id){
        System.out.println("controller "+id);
        try{
            return ResponseEntity.ok().body(flightService.getFlightById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/schedules")
    public List<Flight> getFlightSchedules( @PathVariable String id, @RequestParam(required = false) List<String> dates) {

        return flightService.getFlightSchedules(id, dates);
    }
}
