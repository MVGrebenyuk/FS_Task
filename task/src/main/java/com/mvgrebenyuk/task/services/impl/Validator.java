package com.mvgrebenyuk.task.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvgrebenyuk.task.dto.RequestDto;
import com.mvgrebenyuk.task.exceptions.RequiredSystemException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Validator {

    private final static Integer DEFAULT_HEADER = 0;

    private final Map<String, Map<String, String>> systemInfoFile;

    @Value("${app.header}")
    private String header;
    @Value("${app.systems}")
    private List<String> systems;


    public String validateSystem(String system){

        if(Strings.isEmpty(system)){
            throw new RequiredSystemException("Current system is null");
        }

        if(!systems.contains(system)) {
            throw new RequiredSystemException("Current system is not maintenance");
        }

        return system;
    }

    public String validate(RequestDto request, String system) throws Exception {
        validateSystem(system);

        StringBuilder errors = new StringBuilder();

        systemInfoFile.get(system).entrySet().forEach(entry -> {
                try {
                    Field field = request.getClass().getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    if(!Pattern.compile(entry.getValue()).matcher(String.valueOf(field.get(request))).find()){
                        errors.append("Validation of field  ").append(entry.getKey()).append(" is failed. Pattern = ").append(entry.getValue()).append("\n");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        return errors.toString();
    }

}
