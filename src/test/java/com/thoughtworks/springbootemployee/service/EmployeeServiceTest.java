package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private RetiringEmployeeRepository retiringEmployeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertIterableEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_employee_when_find_employee_by_id_given_employee_id() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        Integer employeeID = 1;

        //when
        Employee actualEmployee = employeeService.findEmployeeById(employeeID);

        //then
        assertEquals(employees.get(0), actualEmployee);
    }

    @Test
    public void should_return_employees_when_find_employees_by_gender_given_gender() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        String gender = "male";

        List<Employee> maleEmployees = new ArrayList<>();
        maleEmployees.add(employees.get(1));
        maleEmployees.add(employees.get(3));

        //when
        List<Employee> actualEmployees = employeeService.findEmployeesByGender(gender);

        //then
        assertIterableEquals(maleEmployees, actualEmployees);
    }

    @Test
    public void should_return_employees_when_find_employees_by_pagination_given_page_index_and_page_size() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        int pageIndex = 1;
        int pageSize = 3;

        List<Employee> pageEmployees = new ArrayList<>();
        pageEmployees.add(employees.get(0));
        pageEmployees.add(employees.get(1));
        pageEmployees.add(employees.get(2));

        //when
        List<Employee> actualEmployees = employeeService.findEmployeesByPagination(pageIndex, pageSize);

        //then
        assertEquals(pageEmployees.size(), actualEmployees.size());
        assertIterableEquals(pageEmployees, actualEmployees);
    }

    @Test
    public void should_add_employee_when_add_employee_given_employee() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);

        Employee employee = new Employee(employees.size() + 1, "alice", 20, "female", 1220);
        List<Employee> actualEmployees = generateEmployees();
        actualEmployees.add(employee);
        //when
        employeeService.addEmployee(employee);

        //then
        assertEquals(employees.size(), actualEmployees.size());
    }

    @Test
    public void should_update_employee_when_update_employee_by_id_employee_given_employee() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        Integer employeeID = 1;
        Employee newEmployee = new Employee(1, "Bruno", 30, "male", 9992);

        //when
        Employee actualEmployee = employeeService.updateEmployeeByID(employeeID, newEmployee);

        //then
        assertEquals(newEmployee.getName(), actualEmployee.getName());
        assertEquals(newEmployee.getAge(), actualEmployee.getAge());
        assertEquals(newEmployee.getSalary(), actualEmployee.getSalary());
    }

    @Test
    public void should_delete_employee_when_delete_employee_by_id_employee_given_employee() {
        //given
        List<Employee> employees = generateEmployees();
        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
        int employeeID = 2;

        List<Employee> actualEmployees = generateEmployees();
        actualEmployees.remove(1);
        //when
        employeeService.deleteEmployeeByID(employeeID);

        //then
        assertEquals(employees.size(), actualEmployees.size());
    }

    public List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "alice", 20, "female", 1000));
        employees.add(new Employee(2, "bob", 20, "male", 1000));
        employees.add(new Employee(3, "bobsy", 20, "female", 1000));
        employees.add(new Employee(4, "mark", 20, "male", 1000));
        return employees;
    }

}
