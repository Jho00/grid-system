package com.sstu.carservice.car;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Car {
    private UUID id;
    private String address;
    private CarStatus status;
}
