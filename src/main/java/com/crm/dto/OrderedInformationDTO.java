package com.crm.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class OrderedInformationDTO {
private Long productId;
	
	private String productCode;
	
	private String productName;
	
	private String size;
	
	private String weight;
	
	private Double purchasePrice;
	
	private Double salePrice;
	
	private Double profit;
	
	private Double netProfit;
	
	private String companyName;
}
