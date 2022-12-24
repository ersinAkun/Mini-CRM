package com.crm.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.domain.OrderedProducts;
import com.crm.requestDTO.SupplierRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.OrderedProductsResponseDTO;
import com.crm.responseDTO.ResponseMessage;
import com.crm.responseDTO.SupplierResponseDTO;
import com.crm.service.SupplierService;




@RestController
@RequestMapping("/supplier")

public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	
	

	
	//***************  create 11.12.2022 ERSIN  ********************
	//POST-http://localhost:8081/supplier/create

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createSupplier(@Valid @RequestBody SupplierRequestDTO supplierRequestDTO){

			supplierService.createSupplier(supplierRequestDTO);

	CrmResponse response = new CrmResponse();
	response.setMessage(ResponseMessage.SUPPLIER_CREATED_MESSAGE);
	response.setSuccess(true);
	return ResponseEntity.ok(response);

}
	
	

	// ********* getAll   17.12.2022 ERSIN *****************

	//GET-http://localhost:8081/supplier/getAll
		@GetMapping("/getAll")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<List<SupplierResponseDTO>> getAllSupplier(){
			
			List<SupplierResponseDTO> allSupplier = supplierService.getAllSupplier();
			return ResponseEntity.ok(allSupplier);
		}
	
	
	// ********  getById   17.12.2022  ERSIN  **************

	//GET - http://localhost:8081/supplier/get/1
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable("id") Long id){
		SupplierResponseDTO supplierResponseDTO = supplierService.getSupplierById(id);
		return ResponseEntity.ok(supplierResponseDTO);
	}
	
	// ******  update  17.12.2022 ERSIN  ******		
	//PUT-http://localhost:8081/supplier/update/3
		@PutMapping("/update/{id}")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<CrmResponse> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierRequestDTO supplierRequestDTO){
			supplierService.updateSupplier(id, supplierRequestDTO);
			CrmResponse crmResponse = new CrmResponse(ResponseMessage.SUPPLIER_UPDATED_MESSAGE, true);
			return ResponseEntity.ok(crmResponse);
			
		}
		
		// ******  pageable  17.12.2022 ERSIN  ******	
		
	
		 @GetMapping("/getPages")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<SupplierResponseDTO>> getAllWithPage(
			@RequestParam("page") int page,
			@RequestParam("size") int size, 
	        @RequestParam("sort") String prop, 
	        @RequestParam("direction") Direction direction  ){
			
			
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop)); 
		
		Page<SupplierResponseDTO> supplierPageDTO = supplierService.getAllWithPage(pageable);
			
		return ResponseEntity.ok(supplierPageDTO);
	
		}	
	
	
	// ******  delete  17.12.2022 ERSIN  ******	
	//DELETE-http://localhost:8081/supplier/4 
		@DeleteMapping("/delete/{id}")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<CrmResponse> deleteSupplier(@PathVariable Long id){
			supplierService.deleteSupplier(id);
			CrmResponse crmResponse = new CrmResponse(ResponseMessage.SUPPLIER_DELETED_MESSAGE, true);
			return ResponseEntity.ok(crmResponse);
			
		}
	
		//*******ERSIN********getOrderedProductsWithSupplierId*****24.12.2022*******
		@GetMapping("/getOrderedProductsBySupplierId/{id}")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<List<OrderedProductsResponseDTO>> getOrderedProductsWithSupplierId(@PathVariable("id") Long supplierId) {

			List<OrderedProductsResponseDTO> productsSupplier = supplierService.getOrderedProductsWithSupplierId(supplierId);

			return ResponseEntity.ok(productsSupplier);
		}
	
	
	
	
	
	

	//sipariş alınmış ürünleri listele	
}
