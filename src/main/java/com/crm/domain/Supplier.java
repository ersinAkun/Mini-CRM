package com.crm.domain;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_supplier")
public class Supplier {
	 @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
		
	 @Column(length = 50, nullable = false)
	  private String name;
	  
	 @Column(length = 30, nullable = false)
	  private String owner;
	  
	 @Column(length = 50, nullable = false)
	  private String address;
	  
	 @Column(length = 30, nullable = false)
	  private String city;
	  
	 @Column(length = 14, nullable = false)
	  private String phone;
	  
	 @Column(length = 14)
	  private String OwnerWhatsapp;
	  
	 @Column(length = 50)
	  private String linkedPage;
	    
	 @Column(length = 50)
	  private String webPage;
	 
	 //@Column
	// private Double totalAmount;  //her satışta üzerine eklencek
	  
	  @OneToMany
	  private List<OrderedProducts> orderedProducts; 
	  //sonradan join coloum yapılıp istediğimiz ismi verebiliriz
	  

}
