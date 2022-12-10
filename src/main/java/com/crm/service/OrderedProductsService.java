package com.crm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.domain.ImageFile;
import com.crm.domain.OrderedProducts;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.ImageFileRepository;
import com.crm.repository.OrderedProductsRepository;
import com.crm.requestDTO.OrderedProductsRequestDTO;
import com.crm.responseDTO.OrderedProductsResponseDTO;



@Service
public class OrderedProductsService {
	
	@Autowired
	OrderedProductsRepository orderedProductsRepository;
	
	@Autowired
	ImageFileService imageFileService;
	
	@Autowired
	ImageFileRepository imageFileRepository;
	
	@Autowired
	SupplierService supplierService;

	
	//******************SAVE PRODUCT*******EMİN*********
	public void saveProduct(OrderedProductsRequestDTO orderedProductsRequestDTO, Long sID, String iID) {
		
		//buraya bu ürün önceden eklenmiş mi onun kontrolünü yapacak bir metod yazmam lazım.
		//bu ürün başka bir suppliera da eklenemesin.
	
		OrderedProducts orderedProducts = new OrderedProducts();
		
		ImageFile imageFile = imageFileService.findImageById(iID);
		
//		Integer usedProductsCount = orderedProductsRepository.findProductCountByImageId(imageFile.getId());
//
//		if (usedProductsCount > 0) {
//			throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);//eğer resim bir üründe kullanılmışsa kontrol et
//		}
		//YUKARDAKİ KISMI SUPPLIER REPOSUNDA oluşturcak da olabilirim. emin olamadım
				
		Set<ImageFile> imFiles = new HashSet<>();
		imFiles.add(imageFile);
		
		orderedProducts.setImage(imFiles);
		orderedProducts.setSupplier(supplierService.findSupplierById(sID));
		orderedProducts.setNetProfit(orderedProductsRequestDTO.getNetProfit());
		orderedProducts.setProductCode(orderedProductsRequestDTO.getProductCode());
		orderedProducts.setProductName(orderedProductsRequestDTO.getProductName());
		orderedProducts.setProfit(orderedProductsRequestDTO.getNetProfit());
		orderedProducts.setPurchasePrice(orderedProductsRequestDTO.getPurchasePrice());
		orderedProducts.setSalePrice(orderedProductsRequestDTO.getSalePrice());
		orderedProducts.setSize(orderedProductsRequestDTO.getSize());
		orderedProducts.setWeight(orderedProductsRequestDTO.getWeight());	
		
		orderedProductsRepository.save(orderedProducts);	
	}

	//********************FIND PRODUCT BY ID********EMIN************
	public OrderedProductsResponseDTO findById(Long id) {
		
		OrderedProducts orderedProducts= orderedProductsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
	
			OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();
			orderedProductsResponseDTO.setNetProfit(orderedProducts.getNetProfit());
			orderedProductsResponseDTO.setProductCode(orderedProducts.getProductCode());
			orderedProductsResponseDTO.setProductName(orderedProducts.getProductName());
			orderedProductsResponseDTO.setPurchasePrice(orderedProducts.getPurchasePrice());
			orderedProductsResponseDTO.setSalePrice(orderedProducts.getSalePrice());
			orderedProductsResponseDTO.setSize(orderedProducts.getSize());
			orderedProductsResponseDTO.setWeight(orderedProducts.getWeight());
			orderedProductsResponseDTO.setSupplierName(orderedProducts.getSupplier().getName());
		
			return orderedProductsResponseDTO;
			
	}
	//***************UPDATE PRODUCT*********EMIN************
	public void updateOrderedProduct(Long id, Long sId, String imageId,
			OrderedProductsRequestDTO orderedProductsRequestDTO) {
		OrderedProducts orderedProducts = getOrderedProduct(id);
		
		ImageFile imageFile = imageFileService.findImageById(imageId);
		Set<ImageFile> imFiles = new HashSet<>();
		imFiles.add(imageFile);
		//burada gönderilen resim  başka bir product'a ait mi onun kontrolü yapılabilir.
		//vison rentte buna benzer birşey yapmıştık
		/*
		 * 	// burada amaç, verilen image daha önce başka araç için kullanılmış mı ?
		List<Car> carList = carRepository.findCarsByImageId(imageFile.getId());
		
		
		for(Car c: carList) {
			// bana gelen car Id si ile yukardakiList türündeki car Id leri eşit olmaları lazım,
			//eğer eşit değilse girilenm image başka bir araç için yüklenmiş
			if(car.getId().longValue()!=c.getId().longValue()) {
				throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
			}
			
		}
		 */
		
		orderedProducts.setImage(imFiles);
		orderedProducts.setSupplier(supplierService.findSupplierById(sId));
		orderedProducts.setNetProfit(orderedProductsRequestDTO.getNetProfit());
		orderedProducts.setProductCode(orderedProductsRequestDTO.getProductCode());
		orderedProducts.setProductName(orderedProductsRequestDTO.getProductName());
		orderedProducts.setProfit(orderedProductsRequestDTO.getNetProfit());
		orderedProducts.setPurchasePrice(orderedProductsRequestDTO.getPurchasePrice());
		orderedProducts.setSalePrice(orderedProductsRequestDTO.getSalePrice());
		orderedProducts.setSize(orderedProductsRequestDTO.getSize());
		orderedProducts.setWeight(orderedProductsRequestDTO.getWeight());
		
		orderedProductsRepository.save(orderedProducts);	
		
		
	}
	
	public OrderedProducts getOrderedProduct (Long id) {
		
		OrderedProducts orderedProducts = orderedProductsRepository.findOrderedProductsById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return orderedProducts;
	}

	public void removeById(Long id) {
		
		OrderedProducts orderedProducts = getOrderedProduct(id);
		//bu ürünü silmeden önce buna ait bitmemiş bir sipariş var mı kontrol etmem lazım!!!
		
		orderedProductsRepository.delete(orderedProducts);
	}

	public List<OrderedProductsResponseDTO> getAllOrderedProducts() {
		
		List<OrderedProducts> orderedProductsList = orderedProductsRepository.findAll();
		
		
		
	}

}
