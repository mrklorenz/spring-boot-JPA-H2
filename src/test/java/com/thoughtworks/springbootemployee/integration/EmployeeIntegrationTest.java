package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_get_all_employees_API() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Markyboy"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[2].name").value("Linne"))
                .andExpect(jsonPath("$[2].age").value(21))
                .andExpect(jsonPath("$[2].gender").value("female"));
    }

    @Test public void should_return_employee_when_get_employee_given_employee_id() throws Exception {
        //given
        int id = 1;
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeID}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gronk"))
                .andExpect(jsonPath("$.age").value(33))
                .andExpect(jsonPath("$.gender").value("male"));
    }




}
