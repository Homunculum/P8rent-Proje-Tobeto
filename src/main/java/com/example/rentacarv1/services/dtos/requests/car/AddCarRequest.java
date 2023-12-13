package com.example.rentacarv1.services.dtos.requests.car;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    private double daily_price;
    private int kilometer;
    private String plate;
    private int year;
}
