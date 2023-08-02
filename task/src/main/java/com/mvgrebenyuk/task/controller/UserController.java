package com.mvgrebenyuk.task.controller;

import com.mvgrebenyuk.task.dto.RequestDto;
import com.mvgrebenyuk.task.dto.ResponseDto;
import com.mvgrebenyuk.task.dto.UserDto;
import com.mvgrebenyuk.task.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserDto getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PostMapping(produces = "application/json")
    public UserDto saveUser(@RequestBody RequestDto request, @RequestHeader("x-Source") String system){
        return service.validateAndSave(request, system);
    }

    @GetMapping(produces = "application/json")
    public List<ResponseDto> getUserByFields(@RequestParam(required = false) String surname, @RequestParam(required = false) String lastName, @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String phone, @RequestParam(required = false) String email){
        return service.findUsersByAnyParams(surname, lastName, name, phone, email);
    }

}
