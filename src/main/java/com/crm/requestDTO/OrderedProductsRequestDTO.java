package com.crm.requestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderedProductsRequestDTO {
	
	@Size(max=15)
	@NotNull(message="Please provide ProductCode")
	@NotBlank
	private String productCode;
	  
	@Size(max=50)
	@NotNull(message="Please provide ProductName")
	@NotBlank
	private String productName;
	  
	@Size(max=25)// 100cmx100cmx100cm
	//@NotNull(message="Please provide Size")
	@NotBlank
	private String size;
	  
	@Size(max=15)
	//@NotNull(message="Please provide weight")
	@NotBlank
	private Integer weight;
	  
	@Size(max=15)
	@NotNull(message="Please provide PurhasePrice")
	@NotBlank
	private Double purchasePrice;
	
	
	
	@Size(max=15)
	@NotNull(message="Please provide SalePrice")
	@NotBlank
    private Double salePrice;
	
	@Size(max=15)
	//@NotNull(message="Please provide NetProfit")
	@NotBlank
    private Double netProfit;
	
}
