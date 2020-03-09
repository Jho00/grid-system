package com.sstu.carservice.appconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigModel {
    private String serverHost;
    private String serverPort;

    private String brokerHost;
    private String brokerPort;

    private List<String> carAddresses;

    private String schedulerInterval;
}
