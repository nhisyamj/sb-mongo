package com.nhisyamj.springboottemplate.dao;

import com.nhisyamj.springboottemplate.vm.EmployeeVM;
import com.nhisyamj.springboottemplate.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl {

    private final EmpDao dao;

    @Autowired
    public EmployeeDaoImpl(EmpDao dao) {
        this.dao = dao;
    }

    public void addEmp(EmployeeVM vm) {
        Employee newEmp = new Employee();
        newEmp.setFirstName(vm.getEmpName());
        newEmp.setRank(vm.getEmpRank());
        newEmp.setDepartment(vm.getDepartment());
        Assert.notNull(vm.getEmpId(),"EmpId is required");
        Employee existingStaff = dao.findByStaffId(vm.getEmpId());
        Assert.isNull(existingStaff,"empId already exists");
        newEmp.setStaffId(vm.getEmpId());
        dao.save(newEmp);
    }

    public void updateEmp(String empId, EmployeeVM vm) {
        Employee existingEmp = dao.findByStaffId(empId);
        Assert.notNull(existingEmp.getStaffId(),"EmpId not found");
        existingEmp.setFirstName(vm.getEmpName());
        existingEmp.setRank(vm.getEmpRank());
        existingEmp.setDepartment(vm.getDepartment());
        dao.save(existingEmp);
    }

    public EmployeeVM getEmpByEmpId(String EmpId) {
        Employee vo = dao.findByStaffId(EmpId);
        EmployeeVM vm = new EmployeeVM();
        vm.setDepartment(vo.getDepartment());
        vm.setEmpName(vo.getFirstName());
        vm.setEmpId(vo.getStaffId());
        vm.setEmpRank(vo.getRank());

        return vm;
    }

    public List<EmployeeVM> getEmpList() {
        Iterable<Employee> employeeVOList = dao.findAll();
        List<EmployeeVM> employeeVMList = new ArrayList<>();
        for (Employee employee : employeeVOList) {
            EmployeeVM employeeVM = new EmployeeVM();
            employeeVM.setEmpRank(employee.getRank());
            employeeVM.setEmpId(employee.getStaffId());
            employeeVM.setEmpName(employee.getFirstName());
            employeeVM.setDepartment(employee.getDepartment());
            employeeVMList.add(employeeVM);
        }

        return employeeVMList;
    }

    public void delEmpbyEmpId(String empId) {
        Employee vo = dao.findByStaffId(empId);
        Assert.notNull(vo,"EmpId not found");
        dao.delete(vo);
    }

}
