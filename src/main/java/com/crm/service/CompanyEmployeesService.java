package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.repository.CompanyEmployeesRepository;

@Service
public class CompanyEmployeesService {
	
	
	@Autowired
	private CompanyEmployeesRepository companyEmployeesRepository;

}
