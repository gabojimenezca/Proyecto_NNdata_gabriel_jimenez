package com.example.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Person {
    private String name;

    private String gender;

    private String identification;

    private String address;

    private String phone;
}
