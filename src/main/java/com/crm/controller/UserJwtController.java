package com.crm.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.requestDTO.CompanyEmployeesRequestDTO;
import com.crm.requestDTO.LoginRequest;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.LoginResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.security.jwt.JwtUtils;
import com.crm.service.CompanyEmployeesService;

@RestController
public class UserJwtController {
	
	// Bu classımda sadece login ve register işlemleri yapılacak
	
   
   @Autowired
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private JwtUtils  jwtUtils;
   
   @Autowired
   private CompanyEmployeesService companyEmployeesService;
  
   
   
   // login
   @PostMapping("/login")
   public ResponseEntity<LoginResponse> authenticate( @Valid @RequestBody LoginRequest loginRequest    )  {
	   
	   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			     new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
	   
	    Authentication authentication  =  authenticationManager.
	    		       authenticate(usernamePasswordAuthenticationToken);
	    
	 UserDetails userDetails  =  (UserDetails) authentication.getPrincipal() ;// mevcut giriş yapan kullanıcıyı getirir
	    
	   String jwtToken =   jwtUtils.generateJwtToken(userDetails);
	   
	   LoginResponse loginResponse = new LoginResponse(jwtToken);
	   
	   return new ResponseEntity<>(loginResponse,HttpStatus.OK);
	   
   }
   
   
 //******CREATE EMPLOYEES****
	
 	@PostMapping("register")
 	public ResponseEntity<CrmResponse> createCompanyEmployees(@Valid @RequestBody CompanyEmployeesRequestDTO companyEmployeesRequestDTO){
 		companyEmployeesService.createCompanyEmployees(companyEmployeesRequestDTO);
 		
 		CrmResponse crmResponse = new CrmResponse();
 		crmResponse.setMessage(ResponseMessage.COMPANY_EMPLOYEES_CREATE_RESPONSE);
 		crmResponse.setSuccess(true);				
 		return ResponseEntity.ok(crmResponse);
 		
 	}
     
   
}
