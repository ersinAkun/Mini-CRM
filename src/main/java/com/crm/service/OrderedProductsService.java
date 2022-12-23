package com.crm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.domain.ImageFile;
import com.crm.domain.OrderedProducts;
import com.crm.domain.Orders;
import com.crm.domain.Supplier;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.ImageFileRepository;
import com.crm.repository.OrderedProductsRepository;
import com.crm.repository.OrdersRepository;
import com.crm.repository.SupplierRepository;
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
	
	@Autowired SupplierRepository supplierRepository;
	
	//@Autowired OrdersService ordersService;
	@Autowired OrdersRepository ordersRepository;

	// ******************SAVE PRODUCT*******EMİN*********
	public void saveProduct(OrderedProductsRequestDTO orderedProductsRequestDTO, Long sID, String iID,Long oId) {

		// buraya bu ürün önceden eklenmiş mi onun kontrolünü yapacak bir metod yazmam
		// lazım.
		// bu ürün başka bir suppliera da eklenemesin.
		ImageFile imageFile = imageFileService.findImageById(iID);
		
		Orders order = ordersRepository.findById(oId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, oId)));
		OrderedProducts orderedProducts = new OrderedProducts();
		

		

//		Integer usedProductsCount = orderedProductsRepository.findProductCountByImageId(imageFile.getId());
//
//		if (usedProductsCount > 0) {
//			throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);//eğer resim bir üründe kullanılmışsa kontrol et
//		}
		// YUKARDAKİ KISMI SUPPLIER REPOSUNDA oluşturcak da olabilirim. emin olamadım

		Set<ImageFile> imFiles = new HashSet<>();
		imFiles.add(imageFile);

		orderedProducts.setImage(imFiles);
		orderedProducts.setSupplier(supplierService.findSupplierById(sID));
		orderedProducts.setProductCode(orderedProductsRequestDTO.getProductCode());
		orderedProducts.setProductName(orderedProductsRequestDTO.getProductName());
		orderedProducts.setPurchasePrice(orderedProductsRequestDTO.getPurchasePrice());
		orderedProducts.setSize(orderedProductsRequestDTO.getSize());
		orderedProducts.setWeight(orderedProductsRequestDTO.getWeight());
		orderedProducts.setOrders(order);

		orderedProductsRepository.save(orderedProducts);
	}

	// ********************FIND PRODUCT BY ID********EMIN************
	public OrderedProductsResponseDTO findById(Long id) {

		OrderedProducts orderedProducts = orderedProductsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		;

		OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();
		orderedProductsResponseDTO.setProductCode(orderedProducts.getProductCode());
		orderedProductsResponseDTO.setProductName(orderedProducts.getProductName());
		orderedProductsResponseDTO.setPurchasePrice(orderedProducts.getPurchasePrice());
		orderedProductsResponseDTO.setSize(orderedProducts.getSize());
		orderedProductsResponseDTO.setWeight(orderedProducts.getWeight());
		orderedProductsResponseDTO.setSupplierName(orderedProducts.getSupplier().getName());

		return orderedProductsResponseDTO;

	}

	// ***************UPDATE PRODUCT*********EMIN************
	public void updateOrderedProduct(Long id, Long sId, String imageId,
			OrderedProductsRequestDTO orderedProductsRequestDTO) {
		OrderedProducts orderedProducts = getOrderedProduct(id);

		ImageFile imageFile = imageFileService.findImageById(imageId);
		Set<ImageFile> imFiles = new HashSet<>();
		imFiles.add(imageFile);
		// burada gönderilen resim başka bir product'a ait mi onun kontrolü yapılabilir.
		// vison rentte buna benzer birşey yapmıştık
		/*
		 * // burada amaç, verilen image daha önce başka araç için kullanılmış mı ?
		 * List<Car> carList = carRepository.findCarsByImageId(imageFile.getId());
		 * 
		 * 
		 * for(Car c: carList) { // bana gelen car Id si ile yukardakiList türündeki car
		 * Id leri eşit olmaları lazım, //eğer eşit değilse girilenm image başka bir
		 * araç için yüklenmiş if(car.getId().longValue()!=c.getId().longValue()) {
		 * throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE); }
		 * 
		 * }
		 */

		orderedProducts.setImage(imFiles);
		orderedProducts.setSupplier(supplierService.findSupplierById(sId));
		orderedProducts.setProductCode(orderedProductsRequestDTO.getProductCode());
		orderedProducts.setProductName(orderedProductsRequestDTO.getProductName());
		orderedProducts.setPurchasePrice(orderedProductsRequestDTO.getPurchasePrice());
		orderedProducts.setSize(orderedProductsRequestDTO.getSize());
		orderedProducts.setWeight(orderedProductsRequestDTO.getWeight());

		orderedProductsRepository.save(orderedProducts);

	}
		//*****EMIN****GET PRODUCT BY ID******************
	public OrderedProducts getOrderedProduct(Long id) {

		OrderedProducts orderedProducts = orderedProductsRepository.findOrderedProductsById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return orderedProducts;
	}
	
	
	//*****EMIN****DELETE PRODUCT BY ID******************
	public void removeById(Long id) {

		OrderedProducts orderedProducts = getOrderedProduct(id);
		// bu ürünü silmeden önce buna ait bitmemiş bir sipariş var mı kontrol etmem
		// lazım!!!

		orderedProductsRepository.delete(orderedProducts);
	}
	//*****EMIN****GET ALL PRODUCTs******************

	public List<OrderedProductsResponseDTO> getAllOrderedProducts() {

		List<OrderedProducts> orderedProductsList = orderedProductsRepository.findAll();

		List<OrderedProductsResponseDTO> dtoList = new ArrayList<>();

		for (OrderedProducts orderedProducts : orderedProductsList) {

			OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();

			orderedProductsResponseDTO.setId(orderedProducts.getId());
			orderedProductsResponseDTO.setProductCode(orderedProducts.getProductCode());
			orderedProductsResponseDTO.setProductName(orderedProducts.getProductName());
			orderedProductsResponseDTO.setPurchasePrice(orderedProducts.getPurchasePrice());
			orderedProductsResponseDTO.setSize(orderedProducts.getSize());
			orderedProductsResponseDTO.setWeight(orderedProducts.getWeight());
			orderedProductsResponseDTO.setSupplierName(orderedProducts.getSupplier().getName());

			dtoList.add(orderedProductsResponseDTO);

		}

		return dtoList;

		// return orderedProductsMapper.pojoListToResponseList(orderedProductsList);

	}
	
	//*****EMIN****GET All PRODUCTS PAGEBLE******************

	public Page<OrderedProductsResponseDTO> findAllWithPage(Pageable pageable) {
		
		Page<OrderedProducts> productsPage = orderedProductsRepository.findAll(pageable);
		
		Page<OrderedProductsResponseDTO> responsePage = productsPage.map(new Function<OrderedProducts, OrderedProductsResponseDTO>() {

			@Override
			public OrderedProductsResponseDTO apply(OrderedProducts orderedProducts) {
				
				OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();
				orderedProductsResponseDTO.setId(orderedProducts.getId());
				orderedProductsResponseDTO.setProductCode(orderedProducts.getProductCode());
				orderedProductsResponseDTO.setProductName(orderedProducts.getProductName());
				orderedProductsResponseDTO.setPurchasePrice(orderedProducts.getPurchasePrice());
				orderedProductsResponseDTO.setSize(orderedProducts.getSize());
				orderedProductsResponseDTO.setWeight(orderedProducts.getWeight());
				orderedProductsResponseDTO.setSupplierName(orderedProducts.getSupplier().getName());

				return orderedProductsResponseDTO;
				
			}
		});
		return responsePage;
	}
	
	
	//******EMIN***GET SUPPLIER PRODUCT'S ************
	public List<OrderedProductsResponseDTO> getProductsWithSupplierId(Long supplierId) {
		
		@SuppressWarnings("unused")
		Supplier supplier= supplierService.findSupplierById(supplierId);// böyle bir id var mı yok mu kontrol et
		
		List<OrderedProducts> orderedProducts = orderedProductsRepository.findProductsWithSupplierId(supplierId);
		
		List<OrderedProductsResponseDTO> dtoList = new ArrayList<>();

		for (OrderedProducts product : orderedProducts) {

			OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();

			orderedProductsResponseDTO.setId(product.getId());
			orderedProductsResponseDTO.setProductCode(product.getProductCode());
			orderedProductsResponseDTO.setProductName(product.getProductName());
			orderedProductsResponseDTO.setPurchasePrice(product.getPurchasePrice());
			orderedProductsResponseDTO.setSize(product.getSize());
			orderedProductsResponseDTO.setWeight(product.getWeight());
			orderedProductsResponseDTO.setSupplierName(product.getSupplier().getName());

			dtoList.add(orderedProductsResponseDTO);

		}

		return dtoList;

		
		
		
	}

	public List<OrderedProductsResponseDTO> getProductsWithOrderId(Long orderId) {
		
		@SuppressWarnings("unused")
		Orders order = ordersRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, orderId)));
		
List<OrderedProducts> orderedProducts = orderedProductsRepository.findProductsWithOrderId(orderId);
		
		List<OrderedProductsResponseDTO> dtoList = new ArrayList<>();

		for (OrderedProducts product : orderedProducts) {

			OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();

			orderedProductsResponseDTO.setId(product.getId());
			orderedProductsResponseDTO.setProductCode(product.getProductCode());
			orderedProductsResponseDTO.setProductName(product.getProductName());
			orderedProductsResponseDTO.setPurchasePrice(product.getPurchasePrice());
			orderedProductsResponseDTO.setSize(product.getSize());
			orderedProductsResponseDTO.setWeight(product.getWeight());
			orderedProductsResponseDTO.setSupplierName(product.getSupplier().getName());

			dtoList.add(orderedProductsResponseDTO);

		}

		return dtoList;

	}

}
