package com.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
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
	
	
	
	
	//******CREATE EMPLOYEES****
	
	@PostMapping("employees/add")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> createCompanyEmployees(@Valid @RequestBody CompanyEmployeesRequestDTO companyEmployeesRequestDTO){
		companyEmployeesService.createCompanyEmployees(companyEmployeesRequestDTO);
		
		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.COMPANY_EMPLOYEES_CREATE_RESPONSE);
		crmResponse.setSuccess(true);
		
		
		return ResponseEntity.ok(crmResponse);
		
	}
		
		
	
	
}
