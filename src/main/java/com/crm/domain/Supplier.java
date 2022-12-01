package com.crm.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.domain.enums.CompanyWhereWasFound;

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
		
	  @Column
	  private String name;
	  
	  @Column
	  private String owner;
	  
	  @Column
	  private String address;
	  
	  @Column
	  private String city;
	  
	  @Column
	  private String phone;
	  
	  @Column
	  private String OwnerWhatsapp;
	  
	  @Column
	  private String linkedPage;
	    
	  @Column
	  private String webPage;
	  
	  @OneToMany
	  private List<OrderedProducts> orderedProducts; 
	  //sonradan join coloum yapılıp istediğimiz ismi verebiliriz
	  

}
