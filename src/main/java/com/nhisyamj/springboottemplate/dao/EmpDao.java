package com.nhisyamj.springboottemplate.dao;

import com.nhisyamj.springboottemplate.vo.EmployeeVO;
import org.springframework.data.repository.CrudRepository;

public interface EmpDao extends CrudRepository<EmployeeVO,Integer> {
    EmployeeVO findByStaffId(String staffId);
}
