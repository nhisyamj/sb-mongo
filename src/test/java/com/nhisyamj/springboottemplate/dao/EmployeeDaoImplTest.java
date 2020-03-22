package com.nhisyamj.springboottemplate.dao;

import com.nhisyamj.springboottemplate.vm.EmployeeVM;
import com.nhisyamj.springboottemplate.vo.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeDaoImplTest {

    @InjectMocks
    private EmployeeDaoImpl employeeDao;

    @Mock
    private EmpDao empDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEmpSuccessTest() {
        EmployeeVM vm = createEmployeeVM();
        Assert.assertNotNull(vm);
        Mockito.when(empDao.save(createEmployeeVO())).thenReturn(createEmployeeVO());
        employeeDao.addEmp(vm);
//        verify(empDao,times(1)).save(createEmployeeVO());
    }

    @Test
    public void updateEmpSuccessTest() {
        EmployeeVM vm = createEmployeeVM();
        Mockito.when(empDao.findByStaffId("")).thenReturn(createEmployeeVO());
        Mockito.when(empDao.save(createEmployeeVO())).thenReturn(createEmployeeVO());
        Assert.assertNotNull(vm);
        employeeDao.updateEmp("",vm);
//        verify(empDao,times(1)).save(createEmployeeVO());
    }

    @Test
    public void getEmpByEmpIdSuccessTest() {
        Mockito.when(empDao.findByStaffId("")).thenReturn(createEmployeeVO());
        EmployeeVM vm = employeeDao.getEmpByEmpId("");
        Assert.assertNotNull(vm);
    }

    @Test
    public void getEmpListSuccessTest() {
        List<Employee> list = new ArrayList<>();
        list.add(createEmployeeVO());

        Mockito.when(empDao.findAll()).thenReturn(list);
        List<EmployeeVM> vmList = employeeDao.getEmpList();
        assertEquals(1,vmList.size());
        verify(empDao,times(1)).findAll();
    }

    public EmployeeVM createEmployeeVM() {
        EmployeeVM vm = new EmployeeVM();
        vm.setEmpId("1234");
        vm.setEmpName("Mat");
        vm.setEmpRank("EC");
        vm.setDepartment("RND");

        return vm;
    }

    public Employee createEmployeeVO() {
        Employee vo = new Employee();
        vo.setFirstName("Ahmad");
        vo.setStaffId("1234");
        vo.setDepartment("RND");

        return vo;
    }


}
