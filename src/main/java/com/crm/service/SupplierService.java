package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository;
	

}
