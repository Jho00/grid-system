package com.sstu.carservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
    private Map<String, String> serviceInformation;
    private Map<String, String> payload;
}
