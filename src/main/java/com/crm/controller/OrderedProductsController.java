package com.crm.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.crm.requestDTO.OrderedProductsRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.OrderedProductsResponseDTO;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.OrderedProductsService;


@RestController
@RequestMapping("/product")
public class OrderedProductsController {

	@Autowired
	OrderedProductsService orderedProductsService;

	// ******************EMİN ***ADD PRODUCT 10.12.22********************//
	@PostMapping("/{sid}/add/") // sid= supplier id... yani bu ürün hangi üreticiye ait onu path'dan alcaz
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> createProduct(
			@Valid @RequestBody OrderedProductsRequestDTO orderedProductsRequestDTO, @PathVariable("sid") Long sID,
			@RequestParam("id") String iID) {// iID ise bu ürüne ait image id'si.

		orderedProductsService.saveProduct(orderedProductsRequestDTO, sID, iID);
		CrmResponse response = new CrmResponse();
		response.setMessage(ResponseMessage.ORDERED_PRODUCT_CREATED_MESSAGE);
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}

	// ********************EMİN ***GET PRODUCT 10.12.22*****************************//

	@GetMapping("/get/{id}")
	public ResponseEntity<OrderedProductsResponseDTO> getOrderById(@PathVariable Long id) {
		OrderedProductsResponseDTO orderedProductsResponseDTO = orderedProductsService.findById(id);
		return ResponseEntity.ok(orderedProductsResponseDTO);
	}

	// ****************EMIN ***UPDATE PRODUCT 10.12.22******************************//

	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> updateOrderedProduct(@RequestParam("id") Long id,
			@RequestParam("imageId") String imageId, @RequestParam("supplierId") Long sId,
			@Valid @RequestBody OrderedProductsRequestDTO orderedProductsRequestDTO) {

		orderedProductsService.updateOrderedProduct(id,sId,imageId,orderedProductsRequestDTO);
		CrmResponse crmResponse = new CrmResponse(ResponseMessage.ORDERED_PRODUCT_UPDATED_MESSAGE,true);
		return ResponseEntity.ok(crmResponse);
		
	}
	
	//*******************EMIN ***DELETE ORDERED PRODUCT 10.12.22*****************//
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CrmResponse> deleteOrderedProduct (@PathVariable Long id){
		orderedProductsService.removeById(id);
		CrmResponse response = new CrmResponse(ResponseMessage.ORDERED_PRODUCT_DELETED_MESSAGE,true);
		return ResponseEntity.ok(response);
	}
	
	//*******************EMIN ***GET ALL ORDERED PRODUCT 10.12.22*****************//
	
	@GetMapping("/getAll")
	public ResponseEntity<List<OrderedProducts>> getAllOrderedProducts(){
		
		List<OrderedProducts> allProducts = orderedProductsService.getAllOrderedProducts();
		
		return ResponseEntity.ok(allProducts);
	}
	
}
