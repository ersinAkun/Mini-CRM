package com.crm.service;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.domain.Supplier;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.SupplierRepository;
import com.crm.requestDTO.SupplierRequestDTO;


@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	
	public Supplier findSupplierById(Long id) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return supplier;
	}

	//***************  create 11.12.2022 ERSIN  ********************
	public void saveSupplier(@Valid SupplierRequestDTO supplierRequestDTO, Long id) {
		Supplier supplier = new Supplier();
		supplier.setName(supplierRequestDTO.getName());
		supplier.setOwner(supplierRequestDTO.getOwnerLastName());//owner a karsilik name ve last name var ben lastname i sectim
		supplier.setAddress(supplierRequestDTO.getAddress());
		supplier.setCity(supplierRequestDTO.getCity());
		supplier.setPhone(supplierRequestDTO.getPhone());
		supplier.setOwnerWhatsapp(supplierRequestDTO.getOwnerWhatsapp());
		supplier.setWebPage(supplierRequestDTO.getWebPage());
		
		supplierRepository.save(supplier);
		
	}


	
	

}
