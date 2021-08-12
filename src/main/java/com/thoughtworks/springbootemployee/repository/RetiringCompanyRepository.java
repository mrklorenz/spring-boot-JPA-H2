package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RetiringCompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public RetiringCompanyRepository() {
        RetiringEmployeeRepository retiringEmployeeRepository = new RetiringEmployeeRepository();
        companies.add(new Company(1, "OOCL", retiringEmployeeRepository.getEmployees().subList(0,2)));
        companies.add(new Company(2, "COSCO", retiringEmployeeRepository.getEmployees().subList(2,4)));
        companies.add(new Company(3, "MAERSK", retiringEmployeeRepository.getEmployees().subList(4,6)));
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
