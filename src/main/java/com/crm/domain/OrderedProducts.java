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
	@Column
	private String productCode;
	@Column
	private String productName;
	@Column
	private String size;
	@Column
	private String weight;
	@Column
	private Double purchasePrice;
	@Column
	private Double salePrice;
	@Column
	private Double profit;
	@Column
	private Double netProfit;
	@Column//null olsun. kayıt ederken biz kendimiz set edelim.
	private String companyName;

}
