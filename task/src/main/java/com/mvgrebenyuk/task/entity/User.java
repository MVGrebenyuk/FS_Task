package com.mvgrebenyuk.task.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
