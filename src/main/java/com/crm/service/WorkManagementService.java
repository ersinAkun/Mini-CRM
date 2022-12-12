package com.crm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.domain.WorkManagement;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.WorkManagementRepository;
import com.crm.requestDTO.WorkManagementRequestDTO;
import com.crm.responseDTO.WorkManagementResponseDTO;

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

	public WorkManagementResponseDTO findById(Long id) {
		WorkManagement workManagement = workManagementRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		WorkManagementResponseDTO workManagemenetResponseDTO = new WorkManagementResponseDTO();
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

	public List<WorkManagementResponseDTO> getAllTasks() {
	
		List<WorkManagement> allTasksList = workManagementRepository.findAll();
		List<WorkManagementResponseDTO> dtoList = new ArrayList<>();
		for (WorkManagement task : allTasksList) {
			
			WorkManagementResponseDTO workManagementResponseDTO = new WorkManagementResponseDTO();
			
			workManagementResponseDTO.setAssigneeName(task.getAssignee().getFirstName());
			workManagementResponseDTO.setCategory(task.getCategory());
			workManagementResponseDTO.setComments(task.getComments());
			workManagementResponseDTO.setDescription(task.getDescription());
			workManagementResponseDTO.setExpectedEndDate(task.getExpectedEndDate());
			workManagementResponseDTO.setFinishedDate(task.getFinishedDate());
			workManagementResponseDTO.setPriority(task.getPriority());
			workManagementResponseDTO.setStartDate(task.getStartDate());
			workManagementResponseDTO.setStatus(task.getStatus());
			workManagementResponseDTO.setTitle(task.getTitle());
			workManagementResponseDTO.setUpdateDate(task.getUpdateDate());
			dtoList.add(workManagementResponseDTO);
		}
		return dtoList;
	}

}
