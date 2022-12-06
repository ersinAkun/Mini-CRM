package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.service.OrderedProductsService;

@RestController
@RequestMapping("/ordered-product")
public class OrderedProductsController {

	@Autowired
	OrderedProductsService orderedProductsService;
	
}
