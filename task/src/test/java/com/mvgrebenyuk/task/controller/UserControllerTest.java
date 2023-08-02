package com.mvgrebenyuk.task.controller;

import com.mvgrebenyuk.task.TaskApplicationTest;
import com.mvgrebenyuk.task.entity.User;
import com.mvgrebenyuk.task.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

class UserControllerTest extends TaskApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init(){
        userRepository.deleteAll();
        userRepository.save(User.builder()
                .name("Maxim")
                .city("Voronezh")
                .birthday(LocalDate.of(2022, 10, 12))
                .bankId(1L)
                .email("Maxim13qwe@gmail.com")
                .surname("Grebenyuk")
                .lastName("Vladimirovich")
                .currentAddress("Voronezh abc home 3 aez")
                .registration("Voronezh abc home 3 aez")
                .passNumber("1417 698222")
                .phone("+79192301244")
                .build());
    }

    @Test
    void getUserByFields() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/")
                        .param("surname", "Grebenyuk")
                        .accept(MediaType.ALL))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());

        mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/")
                                .param("surname", "Grebenyuk")
                                .param("lastName", "Vladimirovich")
                                .accept(MediaType.ALL))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    void saveUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/")
                                .header("x-Source", "mobile")
                                .contentType("application/json")
                                .content("{\"bankId\":1,\"surname\":\"test\",\"name\":\"name\",\"lastName\":\"lastname\",\"birthday\":[2022,10,10],\"passNumber\":\"1417 777777\",\"city\":\"Voronezh\",\"phone\":\"+79192301244\",\"email\":\"Maxim@mail.ru\",\"registration\":\"some address\",\"currentAddress\":\"someAddress\"}\n"))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    void saveUserWithoutHeader() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/")
                                .contentType("application/json")
                                .content("{\"bankId\":1,\"surname\":\"test\",\"name\":\"name\",\"lastName\":\"lastname\",\"birthday\":[2022,10,10],\"passNumber\":\"1417 777777\",\"city\":\"Voronezh\",\"phone\":\"+79192301244\",\"email\":\"Maxim@mail.ru\",\"registration\":\"some address\",\"currentAddress\":\"someAddress\"}\n"))
                .andReturn();

        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void saveUserWithWrongHeader() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/")
                                .header("x-Source", "none")
                                .contentType("application/json")
                                .content("{\"bankId\":1,\"surname\":\"test\",\"name\":\"name\",\"lastName\":\"lastname\",\"birthday\":[2022,10,10],\"passNumber\":\"1417 777777\",\"city\":\"Voronezh\",\"phone\":\"+79192301244\",\"email\":\"Maxim@mail.ru\",\"registration\":\"some address\",\"currentAddress\":\"someAddress\"}\n"))
                .andReturn();

        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void getUserById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/1")
                                .accept(MediaType.ALL))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
    }
}