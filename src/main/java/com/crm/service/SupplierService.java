package com.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.domain.Supplier;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.SupplierRepository;
import com.crm.requestDTO.SupplierRequestDTO;
import com.crm.responseDTO.SupplierResponseDTO;


@Service
public class SupplierService {

	@Autowired


	private SupplierRepository supplierRepository;



	public Supplier findSupplierById(Long id) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return supplier;
	}

	//***************  create 11.12.2022 ERSIN  ********************

	public void createSupplier( SupplierRequestDTO supplierRequestDTO) {
	
		Supplier supplier = new Supplier();
		
		supplier.setName(supplierRequestDTO.getName());//unique 
		supplier.setOwnerFirstName(supplierRequestDTO.getOwnerFirstName());
		supplier.setEmail(supplierRequestDTO.getEmail());
		supplier.setOwnerLastName(supplierRequestDTO.getOwnerLastName());
		supplier.setAddress(supplierRequestDTO.getAddress());
		supplier.setCity(supplierRequestDTO.getCity());
		supplier.setPhone(supplierRequestDTO.getPhone());
		supplier.setOwnerWhatsapp(supplierRequestDTO.getOwnerWhatsapp());
		supplier.setWebPage(supplierRequestDTO.getWebPage());

		supplierRepository.save(supplier);

	}



	public List<SupplierResponseDTO> getAllSupplier() {
		List<Supplier> suppliers = supplierRepository.findAll();
		
		List<SupplierResponseDTO> supplierResponseDTOs = new ArrayList<>();
		
		for(Supplier w : suppliers) {
			
			SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
			
			supplierResponseDTO.setName(w.getName());
			supplierResponseDTO.setEmail(w.getEmail());
			supplierResponseDTO.setOwnerFirstName(w.getOwnerFirstName());
			supplierResponseDTO.setOwnerLastName(w.getOwnerLastName());
			supplierResponseDTO.setAddress(w.getAddress());
			supplierResponseDTO.setCity(w.getCity());
			supplierResponseDTO.setPhone(w.getPhone());
			supplierResponseDTO.setOwnerWhatsapp(w.getOwnerWhatsapp());
			supplierResponseDTO.setWebPage(w.getWebPage());
			
			supplierResponseDTOs.add(supplierResponseDTO);
		}
		return supplierResponseDTOs;
		
	}
	
	

	public SupplierResponseDTO getSupplierById(Long id)  {
	
	Supplier supplier = supplierRepository.findById(id).orElseThrow(()->
	new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

	SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
	
	supplierResponseDTO.setName(supplier.getName());
	supplierResponseDTO.setEmail(supplier.getEmail());
	supplierResponseDTO.setOwnerFirstName(supplier.getOwnerFirstName());
	supplierResponseDTO.setOwnerLastName(supplier.getOwnerLastName());
	supplierResponseDTO.setAddress(supplier.getAddress());
	supplierResponseDTO.setCity(supplier.getCity());
	supplierResponseDTO.setPhone(supplier.getPhone());
	supplierResponseDTO.setOwnerWhatsapp(supplier.getOwnerWhatsapp());
	supplierResponseDTO.setWebPage(supplier.getWebPage());
	
		return supplierResponseDTO;
	}
	
	
	
	
	
	

	public void updateSupplier(Long id,  SupplierRequestDTO supplierRequestDTO) {
		Supplier foundSupplier = supplierRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException(String.format(ErrorMessage.SUPPLIER_UPDATE_RESPONSE_MESSAGE, id)));

		foundSupplier.setName(supplierRequestDTO.getName());
		foundSupplier.setEmail(supplierRequestDTO.getEmail());
		foundSupplier.setOwnerFirstName(supplierRequestDTO.getOwnerFirstName());
		foundSupplier.setOwnerLastName(supplierRequestDTO.getOwnerLastName());
		foundSupplier.setAddress(supplierRequestDTO.getAddress());
		foundSupplier.setCity(supplierRequestDTO.getCity());
		foundSupplier.setPhone(supplierRequestDTO.getPhone());
		foundSupplier.setOwnerWhatsapp(supplierRequestDTO.getOwnerWhatsapp());
		foundSupplier.setWebPage(supplierRequestDTO.getWebPage());

		supplierRepository.save(foundSupplier);
	}

	
	

	public Page<SupplierResponseDTO> getAllWithPage(Pageable pageable){
		
		Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
		Page<SupplierResponseDTO> responsePage = supplierPage.map(new Function<Supplier, SupplierResponseDTO>(){

			public SupplierResponseDTO apply(Supplier supplier) {
				
				SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
					
				supplierResponseDTO.setName(supplier.getName());
				supplierResponseDTO.setEmail(supplier.getEmail());
				supplierResponseDTO.setOwnerFirstName(supplier.getOwnerFirstName());
				supplierResponseDTO.setOwnerLastName(supplier.getOwnerLastName());
				supplierResponseDTO.setAddress(supplier.getAddress());
				supplierResponseDTO.setCity(supplier.getCity());
				supplierResponseDTO.setPhone(supplier.getPhone());
				supplierResponseDTO.setOwnerWhatsapp(supplier.getOwnerWhatsapp());
				supplierResponseDTO.setWebPage(supplier.getWebPage());
					
				return supplierResponseDTO;
			}
			
		});
		return responsePage;
		

	}

	public void deleteSupplier(Long id) throws ResourceNotFoundException{
		Supplier supplier = supplierRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		//supplierRepository.delete(supplier);
		supplierRepository.deleteById(supplier.getId());

	}

	


}