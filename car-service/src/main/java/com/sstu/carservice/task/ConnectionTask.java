package com.sstu.carservice.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sstu.carservice.model.ConnectionPayload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConnectionTask extends DefaultTask {
    @JsonProperty("payload")
    private ConnectionPayload payload;
}
