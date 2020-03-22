package com.nhisyamj.springboottemplate.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String staffId;
    private String department;
    private String rank;
}
