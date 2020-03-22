package com.nhisyamj.springboottemplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhisyamj.springboottemplate.service.EmployeeService;
import com.nhisyamj.springboottemplate.vm.EmployeeVM;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addEmployeeSuccessTest() throws Exception {
        EmployeeVM vm = createEmployee();
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(vm);
        Mockito.doCallRealMethod().when(service).addEmp(vm);
        final ResultActions result = mockMvc.perform(post("/employee")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        result.andExpect(status().isOk());
//        Mockito.verify(service).addEmp(vm);
    }

    @Test
    public void updateEmployeeSuccessTest() throws Exception {
        EmployeeVM vm = createEmployee();
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(vm);
        final ResultActions result = mockMvc.perform(put("/employee")
                .param("emp_id", vm.getEmpId())
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        result.andExpect(status().isOk());
    }

    @Test
    public void getEmpByEmpIdSuccessTest() throws Exception {
        String empId = "";
        final ResultActions result = mockMvc.perform(get("/employee")
                .param("emp_id", empId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        result.andExpect(status().isOk());
    }

    @Test
    public void getEmpListSuccessTest() throws Exception {
        final ResultActions result = mockMvc.perform(get("/employee/list")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        result.andExpect(status().isOk());
    }

    public EmployeeVM createEmployee() {
        EmployeeVM vm = new EmployeeVM();
        vm.setEmpId("");
        vm.setEmpName("");
        vm.setEmpRank("");
        vm.setDepartment("");

        return vm;
    }
}
