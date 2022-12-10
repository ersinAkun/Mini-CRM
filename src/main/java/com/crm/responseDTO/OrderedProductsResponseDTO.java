package com.crm.responseDTO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductsResponseDTO {
	
	
	
	private String productCode;
	  
	 
	private String productName;
	  
	
	private String size;
	  
	
	private Double weight;
	  
	
	private Double purchasePrice;
	  
	 
	private Double salePrice;
		
	
	private Double netProfit;
	
	private String supplierName;
	
	  
	  
}
