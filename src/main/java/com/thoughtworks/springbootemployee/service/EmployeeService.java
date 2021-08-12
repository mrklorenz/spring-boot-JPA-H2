package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private final RetiringEmployeeRepository retiringEmployeeRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeID) {
        return employeeRepository.findAllById(employeeID);
    }

    public List<Employee> findEmployeesByGender(String employeeGender) {
        return employeeRepository.findAllByGender(employeeGender);
    }

    public List<Employee> findEmployeesByPagination(int pageIndex, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeByID(Integer employeeID) {
        employeeRepository.deleteById(employeeID);
    }

    public Employee updateEmployeeByID(Integer employeeID, Employee employeeDetails) {
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeID))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeDetails))
                .get();
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeDetails) {

        if (employeeDetails.getName() != null) employee.setName(employeeDetails.getName());
        if (employeeDetails.getAge() != null) employee.setAge(employeeDetails.getAge());
        if (employeeDetails.getGender() != null) employee.setGender(employeeDetails.getGender());
        if (employeeDetails.getSalary() != null) employee.setSalary(employeeDetails.getSalary());

        return employee;
    }


}
