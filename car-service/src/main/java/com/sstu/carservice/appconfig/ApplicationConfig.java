package com.sstu.carservice.appconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sstu.carservice.model.ConfigModel;

import java.io.File;
import java.io.IOException;

public class ApplicationConfig {

    public static ConfigModel getConfig() {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            return yamlMapper.readValue(new File("src/main/resources/config.yml"), ConfigModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
