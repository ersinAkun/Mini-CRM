package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.repository.OrderedProductsRepository;

@Service
public class OrderedProductsService {
	
	@Autowired
	OrderedProductsRepository orderedProductsRepository;

}
