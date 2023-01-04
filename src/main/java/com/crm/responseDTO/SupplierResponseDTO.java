package com.crm.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SupplierResponseDTO {

	private String name;

	private String email;

	private String ownerFirstName;

	private String ownerLastName;

	private String address;

	private String city;

	private String phone;

	private String OwnerWhatsapp;

	private String linkedPage;

	private String webPage;
	
	
	//order id gösterilsin...

	// private List<OrderedProductsResponseDTO> orderedProducts; // ön tarafa hangi
	// ürünlerden sipariş aldıysa onu da dönelim.

	// private List<OrdersResponseDTO> SupplierOrders; //1-Bu firmanın siparişlerini
	// de ön tarafa dönelim mi?

}