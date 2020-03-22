package com.nhisyamj.springboottemplate.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "emp")
public class EmployeeVO extends Person {
    private String staffId;
    private String department;
    private String rank;
}
