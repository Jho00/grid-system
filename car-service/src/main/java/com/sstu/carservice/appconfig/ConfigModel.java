package com.sstu.carservice.appconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigModel {
    private String serverPort;
    private List<String> cars;
    private String schedulerInterval;
}
