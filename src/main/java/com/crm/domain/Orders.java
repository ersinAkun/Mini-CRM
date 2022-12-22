package com.crm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

	@Column(nullable = false)
	private Double orderAmount;//para türünden miktar

	@Column(length = 250, nullable = false)
	private String rfq;

	@Column(nullable = false)
	private Integer orderQuantity;

	@Column
	private Double totalWeight;

	@Column
	private Double freightCost;//navlun ücreti

	@Column(length = 50)
	private String forwarder;//kargo firması

	@Column

	private LocalDate estimatedDeliveryDate;//planlanan teslim tarihi

	@Column
	private LocalDate deliveryDate;//teslim tarihi(gerçekleşen)

	@Column
	private LocalDate orderDate;//siparişi verdiği tarih


	@Column
	private Double profit; //kar

	@Column
	private Double profitPercentage;//kar yüzdesi

	@Column(length = 250, nullable = false)
	private String notes;

	/*
	 * @ManyToMany(fetch= FetchType.EAGER)  //userdaki role sayisi az old icin eager sectim.. defaultu lazy!
    @JoinTable(name="tbl_user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles = new HashSet<>();
	 */

	@ManyToMany()//mappedBy = "supplier"
	@JoinTable(name="tbl_Order_Supplier",joinColumns = @JoinColumn(name="supplier_id"),inverseJoinColumns = @JoinColumn(name="orders_id"))
	private List<Supplier>supplier = new ArrayList<>();
	
	
	
	
	
	@OneToMany()
	@JoinColumn(name = "orders_id")
	private List<OrderedProducts> orderedProducts;

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