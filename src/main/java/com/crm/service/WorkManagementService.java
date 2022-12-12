package com.crm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.domain.WorkManagement;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.WorkManagementRepository;
import com.crm.requestDTO.WorkManagementRequestDTO;
import com.crm.responseDTO.WorkManagemenetResponseDTO;

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

	public WorkManagemenetResponseDTO findById(Long id) {
		WorkManagement workManagement = workManagementRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		WorkManagemenetResponseDTO workManagemenetResponseDTO = new WorkManagemenetResponseDTO();
		workManagemenetResponseDTO.setAssigneeName(workManagement.getAssignee().getFirstName());
		workManagemenetResponseDTO.setCategory(workManagement.getCategory());
		workManagemenetResponseDTO.setComments(workManagement.getComments());
		workManagemenetResponseDTO.setDescription(workManagement.getDescription());
		workManagemenetResponseDTO.setExpectedEndDate(workManagement.getExpectedEndDate());
		workManagemenetResponseDTO.setFinishedDate(workManagement.getFinishedDate());
		workManagemenetResponseDTO.setPriority(workManagement.getPriority());
		//workManagemenetResponseDTO.setReporter(workManagement); //bunu standart yönetici adını yazıp geçelim mi.
		workManagemenetResponseDTO.setStartDate(workManagement.getStartDate());
		workManagemenetResponseDTO.setStatus(workManagement.getStatus());
		workManagemenetResponseDTO.setTitle(workManagement.getTitle());
		workManagemenetResponseDTO.setUpdateDate(workManagement.getUpdateDate());
		
		return workManagemenetResponseDTO;
		
	}

}
