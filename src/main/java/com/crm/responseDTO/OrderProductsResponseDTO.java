package com.crm.responseDTO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductsResponseDTO {
	
	
	
	private String productCode;
	  
	 
	private String productName;
	  
	
	private String size;
	  
	
	private Integer weight;
	  
	
	private Double purchasePrice;
	  
	 
	private Double salePrice;
	
	  
	
	
	private Double netProfit;
	
	  
	  
}
