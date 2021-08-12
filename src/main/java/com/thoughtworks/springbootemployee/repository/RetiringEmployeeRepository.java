package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RetiringEmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public RetiringEmployeeRepository() {
        employees.add(new Employee(1,"alice",20,"female",1000));
        employees.add(new Employee(2,"popoy",20,"male",1000));
        employees.add(new Employee(3,"basha",20,"female",1000));
        employees.add(new Employee(4,"karyn",20,"female",1000));
        employees.add(new Employee(5,"sza",20,"female",1000));
        employees.add(new Employee(6,"bruno",20,"male",1000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
