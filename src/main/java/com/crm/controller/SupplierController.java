package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	
	
	

}
