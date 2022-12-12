package com.crm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.domain.WorkManagement;
import com.crm.repository.WorkManagementRepository;
import com.crm.requestDTO.WorkManagementRequestDTO;

@Service
public class WorkManagementService {
	
	@Autowired
	WorkManagementRepository workManagementRepository;
	
	@Autowired
	CompanyEmployeesService companyEmployeesService;
	
	//*************CREATE-TASK**EMIN**12.12.22*******
	public void saveTask(@Valid WorkManagementRequestDTO workManagementRequestDTO, Long empId) {
		
		WorkManagement workManagement = new WorkManagement();
		
		LocalDate today = LocalDate.now();
		//String formattedDate = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)); 
		
		workManagement.setAssignee(companyEmployeesService.getCompanyEmployees(empId));
		workManagement.setCategory(workManagementRequestDTO.getCategory());
		workManagement.setComments(workManagementRequestDTO.getComments());
		workManagement.setCreateDate(today);
		workManagement.setCategory(workManagementRequestDTO.getCategory());
		workManagement.setDescription(workManagementRequestDTO.getDescription());
		workManagement.setExpectedEndDate(workManagementRequestDTO.getExpectedEndDate());
		workManagement.setFinishedDate(workManagementRequestDTO.getFinishedDate());
		workManagement.setPriority(workManagementRequestDTO.getPriority());
		workManagement.setStartDate(workManagementRequestDTO.getStartDate());
		workManagement.setStatus(workManagementRequestDTO.getStatus());
		workManagement.setTitle(workManagementRequestDTO.getTitle());
		
		workManagementRepository.save(workManagement);
		
	}

}
