package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private RetiringCompanyRepository retiringCompanyRepository;

    @Test
    public void should_return_all_companies_when_getAllCompanies_given_all_companies() {
        //given
        List<Company> companies = generateCompanies();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);

        //when
        List<Company> actualCompanies = companyService.getAllCompanies();

        //then
        assertIterableEquals(companies, actualCompanies);
    }

    @Test
    public void should_return_company_when_find_company_by_id_given_company_id() {
        //given
        List<Company> companies = generateCompanies();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        Integer companyID = 1;

        //when
        Company actualCompany = companyService.findCompanyByID(companyID);

        //then
        assertEquals(companies.get(0), actualCompany);
    }

    @Test
    public void should_return_employees_when_get_employee_list_given_company_id() {
        //given
        List<Company> companies = generateCompanies();
        RetiringEmployeeRepository retiringEmployeeRepository = new RetiringEmployeeRepository();
        List<Employee> employees = retiringEmployeeRepository.getEmployees().subList(0,2);

        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        Integer companyID = 1;

        //when
        List<Employee> actualEmployees = companyService.getEmployeeList(companyID);

        //then
        assertEquals(employees.get(0).getName(), actualEmployees.get(0).getName());
        assertEquals(employees.size(), actualEmployees.size());
    }

    @Test
    public void should_return_companies_when_find_companies_by_pagination_given_page_index_and_page_size() {
        //given
        List<Company> companies = generateCompanies();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        int pageIndex = 1;
        int pageSize = 3;

        List<Company> pageCompanies = new ArrayList<>();
        pageCompanies.add(companies.get(0));
        pageCompanies.add(companies.get(1));
        pageCompanies.add(companies.get(2));

        //when
        List<Company> actualCompanies = companyService.findCompaniesByPagination(pageIndex, pageSize);

        //then
        assertEquals(pageCompanies.size(), actualCompanies.size());
        assertIterableEquals(pageCompanies, actualCompanies);
    }

    @Test
    public void should_add_company_when_add_company_given_company() {
        //given
        List<Company> companies = generateCompanies();
        RetiringEmployeeRepository newEmployeeRepo = new RetiringEmployeeRepository();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);

        Company company = new Company(1, "OOCL", newEmployeeRepo.getEmployees().subList(0, 2));
        List<Company> actualCompanies = generateCompanies();
        actualCompanies.add(company);
        //when
        companyService.addCompany(company);

        //then
        assertEquals(companies.size(), actualCompanies.size());
    }

    @Test
    public void should_update_company_when_update_company_by_id_given_company_id() {
        //given
        List<Company> companies = generateCompanies();
        RetiringEmployeeRepository newEmployeeRepo = new RetiringEmployeeRepository();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        Integer companyID = 1;
        Company newCompany = new Company(1, "YANGMING", newEmployeeRepo.getEmployees().subList(2,4));

        //when
        Company actualCompany = companyService.updateCompanyByID(companyID, newCompany);

        //then
        assertEquals(newCompany.getName(), actualCompany.getName());
        assertEquals(newCompany.getId(), actualCompany.getId());
        assertEquals(newCompany.getEmployees(),actualCompany.getEmployees());
    }

    @Test
    public void should_delete_company_when_delete_company_by_id_given_company_id() {
        //given
        List<Company> companies = generateCompanies();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        int companyID = 2;

        List<Company> actualCompanies = generateCompanies();
        actualCompanies.remove(1);
        //when
        companyService.deleteCompanyByID(companyID);

        //then
        assertEquals(companies.size(), actualCompanies.size());
    }


    private List<Company> generateCompanies() {
        List<Company> companies = new ArrayList<>();
        RetiringEmployeeRepository newEmployeeRepo = new RetiringEmployeeRepository();
        companies.add(new Company(1, "OOCL", newEmployeeRepo.getEmployees().subList(0, 2)));
        companies.add(new Company(2, "COSCO", newEmployeeRepo.getEmployees().subList(2, 4)));
        companies.add(new Company(3, "MAERSK", newEmployeeRepo.getEmployees().subList(4, 6)));
        return companies;
    }
}
