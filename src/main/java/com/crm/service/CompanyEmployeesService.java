package com.crm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.exception.ConflictException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;


@Service
public class CompanyEmployeesService {
	
	
	@Autowired
	private CompanyEmployeesRepository companyEmployeesRepository;

	public void createCompanyEmployees(CompanyEmployeesRequestDTO companyEmployeesRequestDTO) {
		if (companyEmployeesRepository.existsByEmail(companyEmployeesRequestDTO.getEmail())) {
			throw new ConflictException(
					String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, companyEmployeesRequestDTO.getEmail()));
		}
		
		
		
	}

}
