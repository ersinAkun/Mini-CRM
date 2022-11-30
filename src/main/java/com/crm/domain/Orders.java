package com.crm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	
	@Enumerated
	private Enums shipping;
	@Enumerated
	private Enums orderStatus;
	@Enumerated
	private Enums typeOfDelivery;
	@Enumerated
	private Enums packıngArrangement;
	@Enumerated
	private Enums orderType;
	@Enumerated
	private Enums currency;
	
}
