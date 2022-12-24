package com.crm.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.WorkManagementRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.responseDTO.WorkManagementResponseDTO;
import com.crm.service.WorkManagementService;

@RestController
@RequestMapping("/task-management")
public class WorkManagementController {

	@Autowired
	WorkManagementService workManagementService;

	// *********EMIN**CREATE-TASK***12.12.22***************

	// tarihler genel anlamda kontrol edilmeli.
	// örneğin işin bitiş tarihi create tarihinden önce olmamalı gibi.
	@PostMapping("/create/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createTask(@Valid @RequestBody WorkManagementRequestDTO workManagementRequestDTO,
			@PathVariable("id") Long EmpId) {

		workManagementService.saveTask(workManagementRequestDTO, EmpId);
		CrmResponse response = new CrmResponse();
		response.setMessage(ResponseMessage.TASK_CREATED_MESSAGE);
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}

	// *********EMIN**GET-TASK***12.12.2022**********

	@GetMapping("/get-task/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<WorkManagementResponseDTO> getTask(@PathVariable("id") Long id) {

		WorkManagementResponseDTO workManagemenetResponseDTO = workManagementService.findById(id);
		return ResponseEntity.ok(workManagemenetResponseDTO);
	}

	// ***********EMIN****GET ALL TASKS *** 12.12.22**********

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<WorkManagementResponseDTO>> getAllTasks() {

		List<WorkManagementResponseDTO> allTasks = workManagementService.getAllTasks();
		return ResponseEntity.ok(allTasks);
	}

//***********EMIN****GET ALL TASKS BY PAGEBLE *** 12.12.22**********

	@GetMapping("/pages")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<WorkManagementResponseDTO>> getAllTasksWithPage(

			@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String prop,

			@RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

		Page<WorkManagementResponseDTO> pageDTO = workManagementService.findAllWithPage(pageable);
		return ResponseEntity.ok(pageDTO);
	}

//*************EMIN ***UPDATE TASK 12.12.22**************//

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updateTask(@PathVariable("id") Long id, @RequestParam("eId") Long eId,
			@Valid @RequestBody WorkManagementRequestDTO workManagementRequestDTO) {

		workManagementService.updateTask(id, workManagementRequestDTO, eId);
		CrmResponse crmResponse = new CrmResponse(ResponseMessage.TASK_UPDATED_MESSAGE, true);
		return ResponseEntity.ok(crmResponse);
	}

//*************EMIN ***DELETE TASK 12.12.22**************//

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> deleteTask(@PathVariable Long id) {
		workManagementService.deleteTaskById(id);
		CrmResponse response = new CrmResponse(ResponseMessage.TASK_DELETED_MESSAGE, true);
		return ResponseEntity.ok(response);
	}
	
	//***********EMIN***Get Task With Employee Id***************
	@GetMapping("/getEmployeeTask/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<WorkManagementResponseDTO>> getEmployeeTasks(@PathVariable Long id) {

		List<WorkManagementResponseDTO> allEmployeeTasks = workManagementService.getEmployeeTasks(id);
		return ResponseEntity.ok(allEmployeeTasks);
	}
	
	//**********EMIN***Get Task With Status***************
	@GetMapping("/getTasksWithStatus/{status}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<WorkManagementResponseDTO>> getTasksWithStatus(@PathVariable("status") String status){
		List<WorkManagementResponseDTO> tasksWithStatus = workManagementService.getTasksWithStatus(status);	
		
			return ResponseEntity.ok(tasksWithStatus);	
	
	}
	
	
	//**********EMIN***Get Task With Priority***************
		@GetMapping("/getTasksWithPriority/{priority}")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<List<WorkManagementResponseDTO>> getTasksWithPriority(@PathVariable("priority") String priority){//Enumtype kendi yapısıyla yazmayı dene
			List<WorkManagementResponseDTO> tasksWithPriority = workManagementService.getTasksWithPriority(priority);	
			
				return ResponseEntity.ok(tasksWithPriority);	
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}