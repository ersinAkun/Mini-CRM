package com.crm.controller;

import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//************************* CREATE **************************************
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO){
        companyService.saveCompany(companyRequestDTO);

        CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_CREATED_RESPONSE,true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
//************************* UPDATE **************************************
@PostMapping("/update/{id}")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public ResponseEntity<CrmResponse> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody CompanyRequestDTO companyRequestDTO){
    companyService.updateCompany(id, companyRequestDTO );

    CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_UPDATE_RESPONSE,true);

    return  ResponseEntity.ok(response);

}

//************************* DELETE **************************************
//************************* GET *****************************************
//************************* GET ALL *************************************
//************************* GET PAGEABLE ********************************
//************************* getCompanyWithStatus ************************
//************************* getCompanyWithIndustry **********************
//************************* getCompanyWithType **************************

}
