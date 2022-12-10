package com.crm.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.domain.ImageFile;
import com.crm.domain.OrderedProducts;
import com.crm.repository.ImageFileRepository;
import com.crm.repository.OrderedProductsRepository;
import com.crm.requestDTO.OrderedProductsRequestDTO;


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

}
