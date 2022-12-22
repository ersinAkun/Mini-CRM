package com.crm.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.EmailsResponseDTO;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.EmailsService;

@RestController
@RequestMapping("/emails")
public class EmailsController {


	@Autowired
	private EmailsService emailsService;


	//********CELEBI***********CREATE EMAILS*****************

	/*@PostMapping("/add/{cId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createEmails(@PathVariable Long cId, @Valid @RequestBody EmailsRequestDTO emailsRequestDTO ){
		emailsService.createEmails(cId,emailsRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMAILS_CREATED_RESPONSE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}*/

	//******CELEBI*******GET BY EMAIL**********************
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<EmailsResponseDTO> getEmailById(@PathVariable Long id){
		EmailsResponseDTO emailsResponseDTO = emailsService.getEmailById(id);

		return ResponseEntity.ok(emailsResponseDTO);
	}



	//******CELEBI*******GET ALL EMAIL**********************
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<EmailsResponseDTO>> getAllEmails(){
		List<EmailsResponseDTO> emailsResponseDTOs = emailsService.getAllEmails();

		return ResponseEntity.ok(emailsResponseDTOs);
	}


	//******CELEBI*******GET PAGE EMAIL**********************
	@GetMapping("/getPages")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<EmailsResponseDTO>> getAllEmailsByPage(
			@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestParam("sort") String prop,//neye göre sıralanacağı belirtiliyor
			@RequestParam(value="direction",
					required = false, // direction required olmasın
					defaultValue = "DESC") Direction direction )  {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

		Page<EmailsResponseDTO> emailsResponseDTOPage  =   emailsService.getEmailsPage(pageable);

		return ResponseEntity.ok(emailsResponseDTOPage);

	}

	//******CELEBI*******UPDATE EMAIL**********************
	/*@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updateEmail( @PathVariable Long id, @RequestParam("cId") Long cId, @Valid @RequestBody EmailsRequestDTO emailsRequestDTO ){
		emailsService.updateEmail(id,cId,emailsRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMAILS_UPDATE_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);
	}*/

	//******CELEBI*******GET DELETE EMAIL**********************
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> deleteEmail(@PathVariable Long id){

		emailsService.removeEmailById(id);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMAILS_DELETE_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}













}