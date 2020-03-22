package com.nhisyamj.springboottemplate.dao;

import com.nhisyamj.springboottemplate.vo.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpDao extends MongoRepository<Employee,String> {
    Employee findByStaffId(String staffId);
}
