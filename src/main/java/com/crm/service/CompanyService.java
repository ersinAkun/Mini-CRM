package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.domain.Company;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyRepository;

@Service
public class CompanyService {
@Autowired
private CompanyRepository companyRepository;
//	public Company findCompanyById(Long id) {
//		
//		Company company= companyRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
//	return company;	
//	}
}
