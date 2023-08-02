package com.mvgrebenyuk.task.services.impl;

import com.mvgrebenyuk.task.TaskApplicationTest;
import com.mvgrebenyuk.task.dto.RequestDto;
import com.mvgrebenyuk.task.services.impl.Validator;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ValidatorTest extends TaskApplicationTest {

    @Autowired
    Validator defValidator;

    @Test
    public void validateSuccess() throws Exception {
        RequestDto requestDto = getFullReqDto();

        var res = defValidator.validate(requestDto, "mobile");
        Assertions.assertTrue(Strings.isBlank(res));
        res = defValidator.validate(requestDto, "mail");
        Assertions.assertTrue(Strings.isBlank(res));
        res = defValidator.validate(requestDto, "bank");
        Assertions.assertTrue(Strings.isBlank(res));
        res = defValidator.validate(requestDto, "gosuslugi");
        Assertions.assertTrue(Strings.isBlank(res));

    }

    @Test
    public void validateUnsuccess() throws Exception {
        RequestDto requestDto = getFullReqDto();

        requestDto.setPhone("asdf");
        var res = defValidator.validate(requestDto, "mobile");
        Assertions.assertFalse(Strings.isBlank(res));

    }

    private RequestDto getFullReqDto(){
        return RequestDto.builder()
                .name("Maxim")
                .id(1L)
                .city("Voronezh")
                .birthday(LocalDate.of(2022, 10, 12))
                .bankId(123L)
                .email("Maxim13qwe@gmail.com")
                .surname("Grebenyuk")
                .lastName("Vladimirovich")
                .currentAddress("Voronezh abc home 3 aez")
                .registration("Voronezh abc home 3 aez")
                .passNumber("1417 698222")
                .phone("+79192301244")
                .build();
    }

}