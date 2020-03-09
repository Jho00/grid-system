package com.sstu.carservice.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sstu.carservice.model.ExecutionPayload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExecuteTask extends DefaultTask {
    @JsonProperty("payload")
    private ExecutionPayload payload;
}
