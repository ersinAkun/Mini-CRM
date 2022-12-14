package com.crm.requestDTO;


import java.time.LocalDate;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.crm.domain.OrderedProducts;
import com.crm.domain.Supplier;
import com.crm.domain.enums.CurrencyType;
import com.crm.domain.enums.OrderStatus;
import com.crm.domain.enums.OrderType;
import com.crm.domain.enums.PackingArrangement;
import com.crm.domain.enums.PaymentMethod;
import com.crm.domain.enums.Shipment;
import com.crm.domain.enums.TypeOfDelivery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequestDTO {
	

	//@Size(max = 20)
	//@NotBlank(message = "Please provide Order Amount")
	private Double orderAmount;
	
//	@Size(max = 20)
@NotNull(message = "Please provide RFQ")
	private String rfq;

	//@Size(max = 20)
	@NotNull(message = "Please provide Order Quantity")
	private Integer orderQuantity;
	
	//@Size(max = 20)
	//@NotBlank(message = "Please provide Total Weight")  orderUpdateRequestDTO
	//private Double totalWeight;
	
	//@Size(max = 20, min=1)
	//@NotBlank(message = "Please provide freight Cost")   orderUpdateRequestDTO
	//private Double freightCost;//navlun ücreti
	

//	@NotNull(message = "Please provide Forwarder")    		orderUpdateRequestDTO
//    @Size(max = 20)
//	private String forwarder;//kargo firması
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	//@NotNull(message = "Please provide Estimated Delivery Date")
	private LocalDate estimatedDeliveryDate;//planlanan teslim tarihi
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")  orderUpdateRequestDTO
	//@NotNull(message = "Please provide Delivery Date")
	//private LocalDate deliveryDate;//teslim tarihi(gerçekleşen)
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")   
	@NotNull(message = "Please provide Orders Date")
	private LocalDate orderDate;//siparişi verdiği tarih
	
	//@Size(max = 20)
	//@NotBlank(message = "Please provide profit")		orderUpdateRequestDTO
	//private Double profit;
	
	//@Size(max = 20)
//	@NotBlank(message = "Please provide Profit Percentage")  orderUpdateRequestDTO
	//private Double profitPercentage;
	
	@Size(max = 200)
	private String notes;

	private List<Long> supplierIds;

	private List<Long> orderedProductIds;

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
