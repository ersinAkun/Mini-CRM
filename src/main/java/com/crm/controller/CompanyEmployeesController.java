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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crm.responseDTO.CompanyEmployeesResponseDTO;
import com.crm.service.CompanyEmployeesService;


@RestController
@RequestMapping("/companyEmployees")
public class CompanyEmployeesController {

		//creat+
		//get+
		//getAll
		//get pageble
		//update
		//delete
	
	@Autowired
	private CompanyEmployeesService companyEmployeesService;
	
	//*************GET BY ID EMPLOYEES**********************
	

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CompanyEmployeesResponseDTO> getEmployeesById(@PathVariable Long id){
		CompanyEmployeesResponseDTO companyEmployeesResponseDTO = companyEmployeesService.getEmployeesById(id);
		
		return ResponseEntity.ok(companyEmployeesResponseDTO);
	}
	
	//*************GET ALL EMPLOYEES**********************
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<CompanyEmployeesResponseDTO>> getAllEmployees(){
		List<CompanyEmployeesResponseDTO> companyEmployeesResponseDTO = companyEmployeesService.getAllEmployees();
		
		return ResponseEntity.ok(companyEmployeesResponseDTO);
	}

	
	
	
	//*************GET ALL PAGES EMPLOYEES**********************
//		@GetMapping("/getPages")
//		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//		public ResponseEntity<Page<CompanyEmployeesResponseDTO>> getAllEmployeesByPage(
//							@RequestParam("page") int page,
//							@RequestParam("size") int size,
//							@RequestParam("sort") String prop,//neye göre sıralanacağı belirtiliyor
//							@RequestParam(value="direction",
//							required = false, // direction required olmasın
//							defaultValue = "DESC") Direction direction )  {
//			    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
//			
//			    Page<CompanyEmployeesResponseDTO> companyEmployeesResponseDTOPage  =   companyEmployeesService.getEmployeesPage(pageable);
//			   
//			    return ResponseEntity.ok(companyEmployeesResponseDTOPage);
//			
//		}

		
		
		
		
		
		
		
		
		
		
		
		
		
}
