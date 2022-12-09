package com.crm.requestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
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
	private Double weight;
	  
	@Size(max=15)
	@NotNull(message="Please provide PurhasePrice")
	@NotBlank
	private Double purchasePrice;//fiyat ne türden olcak. tl mi € mu filan?	
	
	@Size(max=15)
	@NotNull(message="Please provide SalePrice")
	@NotBlank
    private Double salePrice;//fiyat ne türden olcak. tl mi € mu filan?	
	
	@Size(max=15)
	//@NotNull(message="Please provide NetProfit")
	@NotBlank
    private Double netProfit;
	
	
//	@Size(max=15)
//	@NotNull(message="Please provide SalePrice")
//	@NotBlank
//	private String supplierName;
	
}
