package com.mvgrebenyuk.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {

    private Long id;

    private Long bankId;

    private String surname;

    private String name;

    private String lastName;

    private LocalDate birthday;

    private String passNumber;

    private String city;

    private String phone;

    private String email;

    private String registration;

    private String currentAddress;

}
