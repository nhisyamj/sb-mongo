package com.nhisyamj.springboottemplate.service;

import com.nhisyamj.springboottemplate.dao.EmployeeDaoImpl;
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

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeDaoImpl empDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEmpSuccessTest() {
        EmployeeVM vm = createEmployeeVM();
        service.addEmp(vm);
        verify(empDao,times(1)).addEmp(vm);
    }

    @Test
    public void updateEmpSuccessTest() {
        EmployeeVM vm = createEmployeeVM();
        String empId = vm.getEmpId();
        service.updateEmp(empId,vm);
        verify(empDao,times(1)).updateEmp(empId,vm);
    }

    @Test
    public void getEmpByEmpIdSuccessTest() {
        EmployeeVM vm = createEmployeeVM();
        String empId = "";
        service.getEmpByEmpId(empId);
        Mockito.when(empDao.getEmpByEmpId(empId)).thenReturn(vm);

        EmployeeVM employeeVM = service.getEmpByEmpId(empId);

        assertEquals("1234",employeeVM.getEmpId());
        assertEquals("Mat",employeeVM.getEmpName());
        assertEquals("EC",employeeVM.getEmpRank());
        assertEquals("RND",employeeVM.getDepartment());
    }

    @Test
    public void getEmpListSuccessTest() {
        List<Employee> list = new ArrayList<>();
        list.add(createEmployeeVO());
        List<EmployeeVM> employeeVMList = new ArrayList<>();
        for (Employee employee : list) {
            EmployeeVM vm = new EmployeeVM();
            vm.setDepartment(employee.getDepartment());
            vm.setEmpRank(employee.getRank());
            vm.setEmpId(employee.getStaffId());
            employeeVMList.add(vm);
        }
        Mockito.when(empDao.getEmpList()).thenReturn(employeeVMList);
        List<EmployeeVM> vms = service.getEmpList();
        Assert.assertEquals(1, vms.size());
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
