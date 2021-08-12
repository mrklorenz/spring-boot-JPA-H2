package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_get_all_companies() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("OOCL"))
                .andExpect(jsonPath("$[1].name").value("COSCO"))
                .andExpect(jsonPath("$[2].name").value("MAERSK"));

    }

    @Test
    public void should_return_company_when_get_company_given_company_id() throws Exception {
        //given
        int id = 1;
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("OOCL"));
    }

    @Test
    public void should_return_employees_when_get_employees_by_company_id_given_company_id() throws Exception {
        //given
        int id = 1;
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Go Goryo"));
    }

    @Test
    public void should_return_companies_when_get_companies_by_pagination_given_page_index_and_size() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .param("pageIndex", "1").param("pageSize", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("OOCL"))
                .andExpect(jsonPath("$[1].name").value("COSCO"))
                .andExpect(jsonPath("$[2].name").value("MAERSK"));
    }


}
