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

import com.crm.requestDTO.LeadRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.LeadResponseDTO;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.LeadService;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	private LeadService leadService;



	// ******* CREATE LEAD ****/
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createLead(@Valid @RequestBody LeadRequestDTO leadRequestDTO) {
		leadService.createLead(leadRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.LEAD_CREATE_RESPONSE);

		crmResponse.setSuccess(true);				
		return ResponseEntity.ok(crmResponse);		
}
	


	// ******* GET LEAD ***********/
	@GetMapping("/getLead/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<LeadResponseDTO> getLeadById(@PathVariable Long id) {
		LeadResponseDTO leadResponseDTO = leadService.getLeadById(id);
		return ResponseEntity.ok(leadResponseDTO);
	}

	// ******** GET ALL LEAD ********/
	@GetMapping("/getAllLead")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<LeadResponseDTO>> getAllLead() {

		List<LeadResponseDTO> allLead = leadService.getAllLead();

		return ResponseEntity.ok(allLead);
	}

	// ******** UPDATE LEAD ************/
// düzenlenecekTODO
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updateLead(@Valid @PathVariable("id") Long id, LeadRequestDTO leadRequestDTO) {
		leadService.updateLead(id, leadRequestDTO);
		CrmResponse crmResponse = new CrmResponse(ResponseMessage.LEAD_UPDATED_MESSAGE, true);
		return ResponseEntity.ok(crmResponse);
	}

//**********  LEAD PAGEABLE  ************/
 
	@GetMapping("/leadPages")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<LeadResponseDTO>> getAllLeadWithPage(

			@RequestParam("page") int page, 
			@RequestParam("size") int size,
			@RequestParam("sort") String prop, 
			
			@RequestParam(value = "direction", 
			              required = false, 
					      defaultValue = "DESC") Direction direction) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

		Page<LeadResponseDTO> pageDTO = leadService.findAllWithPage(pageable);
		return ResponseEntity.ok(pageDTO);
	}
	
	
	//********  DELETE LEAD  **********/
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> deleteLead(@PathVariable Long id){
		leadService.deleteLeadById(id);
		CrmResponse response = new CrmResponse(ResponseMessage.LEAD_DELETED_MESSAGE,true);
		return ResponseEntity.ok(response);
	}


	
	



}
