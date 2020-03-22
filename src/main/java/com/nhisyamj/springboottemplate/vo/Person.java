package com.nhisyamj.springboottemplate.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Person {
    @GeneratedValue
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
}
