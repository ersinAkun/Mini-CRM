package com.crm.dto;

import java.util.Date;

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
public class OrderFollowUpDTO {
private Long id;
	
	private String supplyHouse;
	
	private Double orderAmount;
	
	private String RFQ;
	
	private String whoFoundCustomer;
	
	private String whoContacted;
	
	private Integer orderQuantity;
	
	private Double totalWeight;
	
	private Double freightCost;
	
	private String forwarder;
	
	private Date estimatedDeliverDate;
	
	private Date deliverDate;
	
	private Date dateOfPurchase;
	
	private Double profit;
	
	private Double profitPercentage;
	
	private String notes;
	
	
	
	
}
