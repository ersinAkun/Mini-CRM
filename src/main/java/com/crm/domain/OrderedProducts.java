package com.crm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Ordered_Products")
public class OrderedProducts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long productId;
	  @Column(length = 30, nullable = false)
	private String productCode;
	  
	  @Column(length = 30, nullable = false)
	private String productName;
	  
	  @Column(length = 50)
	private String size;
	  
	  @Column(length = 30)
	private Integer weight;
	  
	  @Column(length = 30, nullable = false)
	private Double purchasePrice;
	  
	  @Column(length = 30, nullable = false)
	private Double salePrice;
	
	  @Column(length = 30)//ön tarafta gözükmesin ama db'de olsun.. @JsonIGnore olabilir
	private Double profit;
	
	  @Column(length = 30)
	private Double netProfit;
	
	  @Column(length = 30)//ön tarafta gözükmesin ama db'de olsun.. @JsonIGnore olabilir
	private String companyName;

}
