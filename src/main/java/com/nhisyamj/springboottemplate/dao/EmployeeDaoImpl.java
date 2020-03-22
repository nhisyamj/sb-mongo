package com.nhisyamj.springboottemplate.dao;

import com.nhisyamj.springboottemplate.vm.EmployeeVM;
import com.nhisyamj.springboottemplate.vo.EmployeeVO;
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
        EmployeeVO newEmp = new EmployeeVO();
        newEmp.setFirstName(vm.getEmpName());
        newEmp.setRank(vm.getEmpRank());
        newEmp.setDepartment(vm.getDepartment());
        Assert.notNull(vm.getEmpId(),"EmpId is required");
        EmployeeVO existingStaff = dao.findByStaffId(vm.getEmpId());
        Assert.isNull(existingStaff,"empId already exists");
        newEmp.setStaffId(vm.getEmpId());
        dao.save(newEmp);
    }

    public void updateEmp(String empId, EmployeeVM vm) {
        EmployeeVO existingEmp = dao.findByStaffId(empId);
        Assert.notNull(existingEmp.getStaffId(),"EmpId not found");
        existingEmp.setFirstName(vm.getEmpName());
        existingEmp.setRank(vm.getEmpRank());
        existingEmp.setDepartment(vm.getDepartment());
        dao.save(existingEmp);
    }

    public EmployeeVM getEmpByEmpId(String EmpId) {
        EmployeeVO vo = dao.findByStaffId(EmpId);
        EmployeeVM vm = new EmployeeVM();
        vm.setDepartment(vo.getDepartment());
        vm.setEmpName(vo.getFirstName());
        vm.setEmpId(vo.getStaffId());
        vm.setEmpRank(vo.getRank());

        return vm;
    }

    public List<EmployeeVM> getEmpList() {
        Iterable<EmployeeVO> employeeVOList = dao.findAll();
        List<EmployeeVM> employeeVMList = new ArrayList<>();
        for (EmployeeVO employeeVO : employeeVOList) {
            EmployeeVM employeeVM = new EmployeeVM();
            employeeVM.setEmpRank(employeeVO.getRank());
            employeeVM.setEmpId(employeeVO.getStaffId());
            employeeVM.setEmpName(employeeVO.getFirstName());
            employeeVM.setDepartment(employeeVO.getDepartment());
            employeeVMList.add(employeeVM);
        }

        return employeeVMList;
    }

    public void delEmpbyEmpId(String empId) {
        EmployeeVO vo = dao.findByStaffId(empId);
        Assert.notNull(vo,"EmpId not found");
        dao.delete(vo);
    }

}
