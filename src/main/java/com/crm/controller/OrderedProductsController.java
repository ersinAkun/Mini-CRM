package com.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.requestDTO.OrderedProductsRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.OrderedProductsService;


@RestController
@RequestMapping("/product")
public class OrderedProductsController {

	@Autowired
	OrderedProductsService orderedProductsService;
	
	@PostMapping("/{sid}/add/")//sid= supplier id... yani bu ürün hangü üreticiye ait onu path'dan alcaz
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createProperty(@Valid @RequestBody OrderedProductsRequestDTO orderedProductsRequestDTO,
			@PathVariable("sid") Long sID, @RequestParam("id") String iID) {//iID ise bu ürüne ait image id'si.

		
		orderedProductsService.saveProduct(orderedProductsRequestDTO,sID,iID);
		CrmResponse response = new CrmResponse();
		response.setMessage(ResponseMessage.ORDERED_PRODUCT_CREATED_MESSAGE);
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}
	
	
	
}
