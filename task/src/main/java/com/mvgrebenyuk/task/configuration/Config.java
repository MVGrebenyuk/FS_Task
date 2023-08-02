package com.mvgrebenyuk.task.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class Config {

    @Value("${app.file}")
    private String jsonValidSetting;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean("systemInfoFile")
    public Map<String, Map<String, String>> systemInfoFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:validation/validationRules.json");
        InputStream inputRes = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputRes));
        Map<String, Map<String, String>> map =  new ObjectMapper().readValue(reader, HashMap.class);
            if(map == null){
                throw new RuntimeException("Not found resource");
            }
        return map;
    }

}
