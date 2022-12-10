package com.crm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.responseDTO.CompanyEmployeesResponseDTO;
import com.crm.service.CompanyEmployeesService;

@RestController
@RequestMapping("/companyEmployees")
public class CompanyEmployeesController {

		//creat
		//get
		//getAll
		//get pageble
		//update
		//delete
	
	@Autowired
	private CompanyEmployeesService companyEmployeesService;
	
	//*************GET BY ID EMPLOYEES**********************
	
	@GetMapping("/{id}/auth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyEmployeesResponseDTO> getEmployeesById(@PathVariable Long id){
		CompanyEmployeesResponseDTO companyEmployeesResponseDTO = companyEmployeesService.getEmployeesById(id);
		
		return ResponseEntity.ok(companyEmployeesResponseDTO);
	}
	
	
	
		
		
	
	
}
