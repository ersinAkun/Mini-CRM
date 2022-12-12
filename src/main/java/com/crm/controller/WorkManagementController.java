package com.crm.controller;

import javax.validation.Valid;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.WorkManagementRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.responseDTO.WorkManagemenetResponseDTO;
import com.crm.service.WorkManagementService;

@RestController
@RequestMapping("/task-management")
public class WorkManagementController {
	
	@Autowired
	WorkManagementService workManagementService;

	//*********EMIN**CREATE-TASK***12.12.22***************
	@PostMapping("/create/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createTask (@Valid 
			@RequestBody WorkManagementRequestDTO workManagementRequestDTO, 
			@PathVariable("id") Long EmpId){
		
		workManagementService.saveTask(workManagementRequestDTO,EmpId);
		CrmResponse response = new CrmResponse();
		response.setMessage(ResponseMessage.TASK_CREATED_MESSAGE);
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}
		
		//*********EMIN**GET-TASK***12.12.2022**********
		
		@GetMapping("/get-task/{id}")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<WorkManagemenetResponseDTO> getTask(@PathVariable("id") Long id){
			
			WorkManagemenetResponseDTO workManagemenetResponseDTO = workManagementService.findById(id);
			return ResponseEntity.ok(workManagemenetResponseDTO);
		}
		
	}
	


