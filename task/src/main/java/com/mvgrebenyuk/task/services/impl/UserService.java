package com.mvgrebenyuk.task.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvgrebenyuk.task.dto.RequestDto;
import com.mvgrebenyuk.task.dto.ResponseDto;
import com.mvgrebenyuk.task.dto.UserDto;
import com.mvgrebenyuk.task.entity.User;
import com.mvgrebenyuk.task.exceptions.ValidationException;
import com.mvgrebenyuk.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectMapper objectMapper;
    private final UserSpecification specification;
    private final Validator validator;
    @SneakyThrows
    public UserDto validateAndSave(RequestDto request, String system) {
           var validateErrors = validator.validate(request, system);

           if(!Strings.isBlank(validateErrors)){
               throw new ValidationException("Validation exception: \n" + validateErrors);
           }

           return objectMapper.convertValue(repository.save(objectMapper.convertValue(request, User.class)), UserDto.class);
    }

    public List<ResponseDto> findUsersByAnyParams(String name, String surname, String lastName,
                                            String phone, String email){
        return repository.findAll(specification.getUserSpecification(name, surname, lastName, phone, email))
                .stream().map(u -> objectMapper.convertValue(u, ResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        return objectMapper.convertValue(repository.findById(id), UserDto.class);
    }
}