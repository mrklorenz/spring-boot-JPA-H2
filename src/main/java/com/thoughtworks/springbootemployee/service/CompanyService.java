package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private final RetiringCompanyRepository retiringCompanyRepository;
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(RetiringCompanyRepository retiringCompanyRepository, CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyByID(Integer companyID) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeeList(Integer companyID) {
        Company company = companyRepository.findById(companyID).orElse(null);
        return company.getEmployees();
    }

    public List<Company> findCompaniesByPagination(int pageIndex, int pageSize) {
        int skipValue = (pageIndex - 1) * pageSize;
        return getAllCompanies()
                .stream()
                .skip(skipValue)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void addCompany(Company company) {
        retiringCompanyRepository.getCompanies().add(new Company(retiringCompanyRepository.getCompanies().size() + 1,
                company.getName(), company.getEmployees()));
    }

    public Company updateCompanyByID(Integer companyID, Company newCompany) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .map(company -> updateCompanyInfo(company, newCompany))
                .get();
    }


    public void deleteCompanyByID(int companyID) {
        getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .ifPresent(company -> retiringCompanyRepository.getCompanies().remove(company));
    }

    private Company updateCompanyInfo(Company company, Company newCompany) {
        if (newCompany.getName() != null) company.setName(newCompany.getName());
        if (newCompany.getEmployees() != null) company.setEmployees(newCompany.getEmployees());
        return company;
    }
}
