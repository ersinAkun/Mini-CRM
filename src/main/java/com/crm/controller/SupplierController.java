package com.crm.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
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

import com.crm.domain.Supplier;
import com.crm.requestDTO.SupplierRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.SupplierService;

import lombok.AllArgsConstructor;

@AutoConfiguration 

@RestController
@RequestMapping("/supplier")
@AllArgsConstructor
public class SupplierController {
	
	//@Autowired
	private SupplierService supplierService;
	
	//***************  create 11.12.2022 ERSIN  ********************
	//POST-http://localhost:8081/supplier/create
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CrmResponse> createSupplier(@Valid @RequestBody SupplierRequestDTO supplierRequestDTO,
			@PathVariable Long id){
			supplierService.saveSupplier(supplierRequestDTO, id);

	CrmResponse response = new CrmResponse(ResponseMessage.SUPPLIER_CREATED_MESSAGE, true);
	
	return new ResponseEntity<>(response,HttpStatus.CREATED);

}
	

	// ********* getAll   17.12.2022 ERSIN *****************

	//GET-http://localhost:8081/supplier
		@GetMapping
		public ResponseEntity<List<Supplier>> getAllSupplier(){
			
			List<Supplier> list = supplierService.getAll();
			return ResponseEntity.ok(list);
		}
	
	
	// ********  getById   17.12.2022  ERSIN  **************

	//GET - http://localhost:8081/supplier/1
	@GetMapping("/{id}")
	public ResponseEntity<Supplier> getSupplier(@PathVariable("id") Long id){
		Supplier supplier = supplierService.getSupplier(id);
		return ResponseEntity.ok(supplier);
	}
	
	// ******  update  17.12.2022 ERSIN  ******		
	//PUT-http://localhost:8081/supplier/3
		@PutMapping("/{id}")
		public ResponseEntity<Map<String, String>> updateSuppplier(@PathVariable Long id, @Valid @RequestBody Supplier supplier){
			supplierService.updateSupplier(id, supplier);
			Map<String, String> map=new HashMap<>();
			map.put("message", "Supplier Successfully created");
			map.put("status", "true");
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}
		
		// ******  pageable  17.12.2022 ERSIN  ******	
		
	//GET - http://localhost:8081/contactmessage/pages?page=1&size=3&sort=id&direction=ASC
	@GetMapping("/pages")
	public ResponseEntity<Page<Supplier>> getAllWithPage(
			@RequestParam("page") int page,@RequestParam("size") int size, 
	        @RequestParam("sort") String prop, @RequestParam("direction") Direction direction  ){
			
			
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));  
		Page<Supplier> supplierPage = supplierService.getAllWithPage(pageable);
			
		return ResponseEntity.ok(supplierPage);
	
		}	
	
	
	// ******  delete  17.12.2022 ERSIN  ******	
	//DELETE-http://localhost:8081/supplier/4 
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, String>> deleteSupplier(@PathVariable Long id){
			supplierService.deleteSupplier(id);
			Map<String, String> map=new HashMap<>();
			map.put("message", "Supplier Successfully Deleted");
			map.put("status", "true");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
	
	
	
	
	
	
	
	
	//sipariş alınmış ürünleri listele	
}
