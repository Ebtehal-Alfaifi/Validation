package com.example.validateq3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EvM {
    @NotEmpty(message = "ID cannot be null")
    @Size(min = 3, message = "ID must be more than 2 characters")
    private String id;

    @NotEmpty(message = "Description cannot be null")
    @Size(min = 16, message = "Description must be more than 15 characters")
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 26, message = "Capacity must be more than 25")
    private Integer capacity;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "You should enter a start date in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;




    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "you should enter end date in future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}

