package com.crm.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.SupplierRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.SupplierService;

@AutoConfiguration 

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	//***************  create 11.12.2022 ERSIN  ********************
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> createSupplier(@Valid @RequestBody SupplierRequestDTO supplierRequestDTO,
			@PathVariable Long id){
			supplierService.saveSupplier(supplierRequestDTO, id);

	CrmResponse response = new CrmResponse();
	response.setMessage(ResponseMessage.SUPPLIER_CREATED_MESSAGE);
	response.setSuccess(true);
	return ResponseEntity.ok(response);

}
	
	
	
	
	//get
	//get all
	//get pageble 
	//update
	//delete
	//sipariş alınmış ürünleri listele	
}
