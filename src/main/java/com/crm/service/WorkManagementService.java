package com.crm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crm.domain.CompanyEmployees;
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
	
	LocalDate today = LocalDate.now();
	
	//*************CREATE-TASK**EMIN**12.12.22*******
	public void saveTask(@Valid WorkManagementRequestDTO workManagementRequestDTO, Long empId) {
		
		WorkManagement workManagement = new WorkManagement();
		
		
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
		workManagement.setUpdateDate(workManagementRequestDTO.getUpdateDate());
		
		
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

	public Page<WorkManagementResponseDTO> findAllWithPage(Pageable pageable) {
		
		Page<WorkManagement> tasksPage = workManagementRepository.findAll(pageable);
		
		Page<WorkManagementResponseDTO> responsePage = tasksPage.map(new Function<WorkManagement, WorkManagementResponseDTO>() {
			
			
			@Override
			public WorkManagementResponseDTO apply(WorkManagement workManagement) {
				
				WorkManagementResponseDTO workManagementResponseDTO = new WorkManagementResponseDTO();
				
				workManagementResponseDTO.setAssigneeName(workManagement.getAssignee().getFirstName());
				workManagementResponseDTO.setCategory(workManagement.getCategory());
				workManagementResponseDTO.setComments(workManagement.getComments());
				workManagementResponseDTO.setDescription(workManagement.getDescription());
				workManagementResponseDTO.setExpectedEndDate(workManagement.getExpectedEndDate());
				workManagementResponseDTO.setFinishedDate(workManagement.getFinishedDate());
				workManagementResponseDTO.setPriority(workManagement.getPriority());
				workManagementResponseDTO.setStartDate(workManagement.getStartDate());
				workManagementResponseDTO.setStatus(workManagement.getStatus());
				workManagementResponseDTO.setTitle(workManagement.getTitle());
				workManagementResponseDTO.setUpdateDate(workManagement.getUpdateDate());
				
				return workManagementResponseDTO;
				
			}
		});
		
		return responsePage;
	
	}

	public void updateTask(Long id, @Valid WorkManagementRequestDTO workManagementRequestDTO,Long eId) {
		
		WorkManagement workManagement = workManagementRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
		
				workManagement.setAssignee(companyEmployeesService.getCompanyEmployees(eId));
				workManagement.setCategory(workManagementRequestDTO.getCategory());
				workManagement.setComments(workManagementRequestDTO.getComments());
				workManagement.setCreateDate(workManagementRequestDTO.getCreateDate());
				workManagement.setCategory(workManagementRequestDTO.getCategory());
				workManagement.setDescription(workManagementRequestDTO.getDescription());
				workManagement.setExpectedEndDate(workManagementRequestDTO.getExpectedEndDate());
				workManagement.setFinishedDate(workManagementRequestDTO.getFinishedDate());
				workManagement.setPriority(workManagementRequestDTO.getPriority());
				workManagement.setStartDate(workManagementRequestDTO.getStartDate());
				workManagement.setStatus(workManagementRequestDTO.getStatus());
				workManagement.setTitle(workManagementRequestDTO.getTitle());
				workManagement.setUpdateDate(workManagementRequestDTO.getUpdateDate());
				
				workManagementRepository.save(workManagement);
				
	}

	public void deleteTaskById(Long id) {
		WorkManagement workManagement = workManagementRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		workManagementRepository.delete(workManagement);
	}

	public List<WorkManagementResponseDTO> getEmployeeTasks(Long id) {
		//burada aldığım id employee id... ona atanan işleri bulabilmek için
		@SuppressWarnings("unused")
		CompanyEmployees employee= companyEmployeesService.getCompanyEmployees(id);
		List<WorkManagement>  workManagementList= workManagementRepository.findTaskWithEmployeeId(id);
		
		List<WorkManagementResponseDTO> dtoList = new ArrayList<>();
		for (WorkManagement task : workManagementList) {
			
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
