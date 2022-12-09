package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.domain.Supplier;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.SupplierRepository;


@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	
	public Supplier findSupplierById(Long id) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return supplier;
	}
	

}
