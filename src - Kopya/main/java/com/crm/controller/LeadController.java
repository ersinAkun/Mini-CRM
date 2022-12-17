package com.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.LeadRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.LeadService;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	private LeadService leadService;
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createLead(@Valid   @RequestBody LeadRequestDTO leadRequestDTO){
		leadService.createLead(leadRequestDTO);
		
		
		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.LEAD_CREATE_RESPONSE);
		crmResponse.setSuccess(true);				
		return ResponseEntity.ok(crmResponse);
		
}
}
