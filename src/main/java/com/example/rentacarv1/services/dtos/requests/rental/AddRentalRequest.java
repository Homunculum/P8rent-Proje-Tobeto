package com.example.rentacarv1.services.dtos.requests.rental;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @Positive(message = "enter only positive")
    private int startKilometer;

    @Positive(message = "enter only positive")
    private Long endKilometer;

    @Positive(message = "enter only positive")
    private Double totalPrice;

    @Min(value = 0,message = "The discount value can not be lower than 0")
    private Double discount;

    @Positive(message = "enter only positive")
    @Max(value = 2,message = "Enter the Car ID as a number between zero and two.")
    private int car;

    @Positive(message = "enter only positive")
    @Max(value = 2,message = "Enter the customer ID as a number between zero and two.")
    private int customer;

    @Positive(message = "enter only positive")
    @Max(value = 999999,message = "Enter the employee ID as a number")
    private int employee;

}
