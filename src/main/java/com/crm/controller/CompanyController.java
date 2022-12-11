package com.crm.controller;

import com.crm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//************************* CREATE **************************************
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<> createCompany(){

    }
//************************* UPDATE **************************************
//************************* DELETE **************************************
//************************* GET *****************************************
//************************* GET ALL *************************************
//************************* GET PAGEABLE ********************************
//************************* getCompanyWithStatus ************************
//************************* getCompanyWithIndustry **********************
//************************* getCompanyWithType **************************

}
