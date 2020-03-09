package com.sstu.carservice.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import com.sstu.carservice.model.ConnectionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConnectionPayload.class, name = "payload")
})
public class DefaultTask implements Task {
    private String action;
}
