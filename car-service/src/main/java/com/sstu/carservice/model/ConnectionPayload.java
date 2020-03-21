package com.sstu.carservice.model;

import lombok.Data;

@Data
public class ConnectionPayload {
    private String address;
    private String port;
}
