package com.example.jobapp.service;

import com.example.jobapp.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    boolean updateCompany(Long id, Company updatedCompany);

    void createCompany(Company company);

    boolean deleteById(Long id);

    Company getCompanyById(Long id);
}
