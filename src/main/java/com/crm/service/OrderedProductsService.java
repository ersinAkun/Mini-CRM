package com.crm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.repository.OrderedProductsRepository;
import com.crm.requestDTO.OrderedProductsRequestDTO;

@Service
public class OrderedProductsService {
	
	@Autowired
	OrderedProductsRepository orderedProductsRepository;

	public void saveProduct(OrderedProductsRequestDTO orderedProductsRequestDTO, Long sID, String iID) {
		
		
		//
		
	}

}
