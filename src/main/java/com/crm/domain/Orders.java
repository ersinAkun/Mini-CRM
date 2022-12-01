package com.crm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.crm.domain.enums.CurrencyType;
import com.crm.domain.enums.OrderStatus;
import com.crm.domain.enums.OrderType;
import com.crm.domain.enums.PackingArrangement;
import com.crm.domain.enums.PaymentMethod;
import com.crm.domain.enums.Shipment;
import com.crm.domain.enums.TypeOfDelivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_orders")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String supplyHouse;
	@Column
	private Double orderAmount;
	@Column
	private String RFQ;

	@Column
	private Integer orderQuantity;
	@Column
	private Double totalWeight;
	@Column
	private Double freightCost;
	@Column
	private String forwarder;
	@Column
	private Date estimatedDeliverDate;
	@Column
	private Date deliverDate;
	@Column
	private Date dateOfPurchase;
	@Column
	private Double profit;
	@Column
	private Double profitPercentage;
	@Column
	private String notes;
	
	//@ManyToOne
	//private String whoFoundCustomer;
	
	//@Column
	//private String whoContacted;
	
		
	@Enumerated(EnumType.STRING)
	private Shipment shipping;
	@Enumerated(EnumType.STRING)
	private  OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private TypeOfDelivery typeOfDelivery;
	@Enumerated(EnumType.STRING)
	private  PackingArrangement packingArrangement;
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;	
}
