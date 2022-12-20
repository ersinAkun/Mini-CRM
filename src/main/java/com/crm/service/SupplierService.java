package com.crm.service;

import java.util.List;

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

	public void createSupplier(@Valid SupplierRequestDTO supplierRequestDTO) {
		
		
		

		Supplier supplier = new Supplier();
		
		supplier.setName(supplierRequestDTO.getName());//unique 

		supplier.setOwner(supplierRequestDTO.getOwnerLastName());//owner a karsilik name ve last name var ben lastname i sectim

		supplier.setEmail(supplierRequestDTO.getEmail());
		supplier.setOwner(supplierRequestDTO.getOwnerName());//owner a karsilik name ve last name var ben lastname i sectim

		supplier.setAddress(supplierRequestDTO.getAddress());
		supplier.setCity(supplierRequestDTO.getCity());
		supplier.setPhone(supplierRequestDTO.getPhone());
		supplier.setOwnerWhatsapp(supplierRequestDTO.getOwnerWhatsapp());
		supplier.setWebPage(supplierRequestDTO.getWebPage());

		supplierRepository.save(supplier);

	}





	public List<Supplier> getAll() {

		return supplierRepository.findAll();
	}

	public Supplier getSupplier(Long id) throws ResourceNotFoundException{

		return supplierRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
	}

	public void updateSupplier(Long id,  Supplier newSupplier) {
		Supplier foundSupplier = getSupplier(id);

		foundSupplier.setName(newSupplier.getName());
		foundSupplier.setEmail(newSupplier.getEmail());
		foundSupplier.setOwner(newSupplier.getOwner());
		foundSupplier.setAddress(newSupplier.getAddress());
		foundSupplier.setCity(newSupplier.getCity());
		foundSupplier.setPhone(newSupplier.getPhone());
		foundSupplier.setOwnerWhatsapp(newSupplier.getOwnerWhatsapp());
		foundSupplier.setWebPage(newSupplier.getWebPage());

		supplierRepository.save(foundSupplier);
	}


	public Page<Supplier> getAllWithPage(Pageable pageable){
		return supplierRepository.findAll(pageable);

	}

	public void deleteSupplier(Long id) throws ResourceNotFoundException{
		Supplier supplier = getSupplier(id);
		//supplierRepository.delete(supplier);
		supplierRepository.deleteById(supplier.getId());

	}

<<<<<<< HEAD

	
=======
>>>>>>> 8de7ea747268f8187d47596f181c68d1bfc076c7


}