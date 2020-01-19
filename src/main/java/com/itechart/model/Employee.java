package com.itechart.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Employee {

    private UUID id;
    private int age;
    private String name;
    private String position;
    private int salary;

}
