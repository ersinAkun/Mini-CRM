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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdateAdminRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdatePasswordRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdateRequestDTO;
import com.crm.responseDTO.CompanyEmployeesResponseDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.CompanyEmployeesService;

@RestController
@RequestMapping("/companyEmployees")
public class CompanyEmployeesController {

	@Autowired
	private CompanyEmployeesService companyEmployeesService;

	// ******CREATE EMPLOYEES****

	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> createCompanyEmployees(
			@Valid @RequestBody CompanyEmployeesRequestDTO companyEmployeesRequestDTO) {
		companyEmployeesService.createCompanyEmployees(companyEmployeesRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.COMPANY_EMPLOYEES_CREATE_RESPONSE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}

	// ******CELEBI*******GET BY ID EMPLOYEES**********************
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CompanyEmployeesResponseDTO> getEmployeesById(@PathVariable Long id) {
		CompanyEmployeesResponseDTO companyEmployeesResponseDTO = companyEmployeesService.getEmployeesById(id);

		return ResponseEntity.ok(companyEmployeesResponseDTO);
	}

	// *******CELEBI******GET ALL EMPLOYEES**********************

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<CompanyEmployeesResponseDTO>> getAllEmployees() {
		List<CompanyEmployeesResponseDTO> companyEmployeesResponseDTO = companyEmployeesService.getAllEmployees();

		return ResponseEntity.ok(companyEmployeesResponseDTO);
	}

	@GetMapping("/getPages")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<CompanyEmployeesResponseDTO>> getAllEmployeesByPage(@RequestParam("page") int page,
			@RequestParam("size") int size, @RequestParam("sort") String prop, // neye göre sıralanacağı belirtiliyor
			@RequestParam(value = "direction", required = false, // direction required olmasın
					defaultValue = "DESC") Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

		Page<CompanyEmployeesResponseDTO> companyEmployeesResponseDTOPage = companyEmployeesService
				.getEmployeesPage(pageable);

		return ResponseEntity.ok(companyEmployeesResponseDTOPage);

	}


	

	// ********CELEBI*****UPDATE LOGIN EMPLOYEES**********************
	@PutMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updateLoginEmployees(
			@Valid @RequestBody CompanyEmployeesUpdateRequestDTO companyEmployeesUpdateRequestDTO) {
		companyEmployeesService.updateLoginEmployees(companyEmployeesUpdateRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMPLOYEES_UPDATE_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}

	// ********CELEBI*****UPDATE ADMIN EMPLOYEES**********************

	@PutMapping("/updateAdmin")

	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> updateCompanyEmployees(@RequestParam("id") Long id,
			@Valid @RequestBody CompanyEmployeesUpdateAdminRequestDTO companyEmployeesUpdateAdminRequestDTO) {
		companyEmployeesService.updateEmployees(id, companyEmployeesUpdateAdminRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMPLOYEES_UPDATE_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);
	}

	// ********CELEBI*****UPDATE PASSWORD LOGIN EMPLOYEES**********************
	@PatchMapping("/auth")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updatePassword(
			@Valid @RequestBody CompanyEmployeesUpdatePasswordRequestDTO companyEmployeesUpdatePasswordRequestDTO) {
		companyEmployeesService.updatePassword(companyEmployeesUpdatePasswordRequestDTO);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.PASSWORD_CHANGED_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}

	// ********CELEBI*****DELETE EMPLOYEES**********************
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> deleteCompanyEmployees(@PathVariable Long id) {

		companyEmployeesService.removeEmployeesById(id);

		CrmResponse crmResponse = new CrmResponse();
		crmResponse.setMessage(ResponseMessage.EMPLOYEES_DELETE_RESPONSE_MESSAGE);
		crmResponse.setSuccess(true);
		return ResponseEntity.ok(crmResponse);

	}


}
