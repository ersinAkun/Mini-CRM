package com.crm.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.CompanyResponseDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//************************* CREATE **************************************

   


    @PostMapping("/create/{eId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO ,@PathVariable Long eId){
        companyService.saveCompany(companyRequestDTO,eId );




        CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_CREATED_RESPONSE,true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
//************************* UPDATE **************************************
//@PostMapping("/update/{id}")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//public ResponseEntity<CrmResponse> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody CompanyRequestDTO companyRequestDTO){
//    companyService.updateCompany(id, companyRequestDTO );
//
//    CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_UPDATE_RESPONSE,true);
//
//    return  ResponseEntity.ok(response);
//
//}

//************************* DELETE **************************************
//************************* GET *****************************************

//    @GetMapping("/get/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long id) {
//        CompanyResponseDTO companyResponseDTO =companyService.getCompanyById(id);
//        return ResponseEntity.ok(companyResponseDTO);
//    }

//************************* GET ALL *************************************
//************************* GET PAGEABLE ********************************
//************************* getCompanyWithStatus ************************
//************************* getCompanyWithIndustry **********************
//************************* getCompanyWithType **************************

}
