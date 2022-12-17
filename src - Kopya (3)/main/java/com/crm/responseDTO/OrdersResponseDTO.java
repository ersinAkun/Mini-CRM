package com.crm.responseDTO;

import java.util.Date;
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
public class OrdersResponseDTO {
	
	private Double orderAmount;
		
	private String RFQ;
	
	private Integer orderQuantity;
		
	private Double totalWeight;
	
	private Double freightCost;//navlun ücreti
	
	private String forwarder;//kargo firması

	private Date estimatedDeliveryDate;//planlanan teslim tarihi
	
	private Date deliveryDate;//teslim tarihi(gerçekleşen)
	
	private Date orderDate;//siparişi verdiği tarih
	
	private Double profit;
	
	private Double profitPercentage;
	
	private String notes;
	
	private Shipment shipping;
	
	private  OrderStatus orderStatus;
	
	private TypeOfDelivery typeOfDelivery;
	
	private  PackingArrangement packingArrangement;
	
	private OrderType orderType;
	
	private CurrencyType currencyType;	
	
	private PaymentMethod paymentMethod;	
	

}
