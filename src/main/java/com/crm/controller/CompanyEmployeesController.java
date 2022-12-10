package com.crm.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
