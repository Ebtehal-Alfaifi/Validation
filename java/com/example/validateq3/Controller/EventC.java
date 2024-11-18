package com.example.validateq3.Controller;

import com.example.validateq3.Api.ApiResponse;
import com.example.validateq3.Model.EvM;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventC {
    ArrayList<EvM> events = new ArrayList<>();

    // Display all events
    @GetMapping("/display")
    public ArrayList<EvM> getEvents() {
        return events;
    }

    // Add new event
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid EvM event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Add success"));
    }

    // Update event
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid EvM event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Update success"));
    }

    // Delete event
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("Delete success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Delete failed: Index out of bounds"));
    }

    // Change capacity
    @PutMapping("/capacity/{id}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable String id, @PathVariable int capacity) {
        for (EvM event : events) {
            if (event.getId().equals(id)) {
                event.setCapacity(capacity);
                return ResponseEntity.status(200).body(new ApiResponse("Capacity updated successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
    }

    // Search event by ID
    @GetMapping("/search/{id}")
    public ResponseEntity<EvM> searchEvent(@PathVariable String id) {
        for (EvM event : events) {
            if (event.getId().equals(id)) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body(null);
    }
}

