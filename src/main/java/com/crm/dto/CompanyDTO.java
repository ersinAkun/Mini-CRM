package com.crm.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.crm.domain.Emails;
import com.crm.domain.Lead;
import com.crm.domain.Orders;
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
public class CompanyDTO {
	 
		
	  @Column(length = 30, nullable = false)
	  private String name;
	  
	  @Column(length = 30, nullable = false)
	  private String owner;
	  
	  @Column(length = 90, nullable = false)
	  private String address;
	  
	  @Column(length = 30, nullable = false)
	  private String country;
	  
	  @Column(length = 30, nullable = false)
	  private String city;
	  
	  @Column(length = 14, nullable = false)
	  private String phone;
	  
	  @Column(length = 14)
	  private String leadWhatsapp;
	 
	  @Column(length = 90)
	  private String lastActivity;
	  
	  @Column(nullable = false)
	  private Date lastActivityDate;
	  
	  @Column(length = 30)
	  private String linkedPage;
	  
	  @Column(length = 10, nullable = false)
	  private String timeZone;
	  
	  @Column(length = 30)
	  private String webPage;
	  
	  @Column(length = 250, nullable = false)
	  private String RFQ;
	  
	  @Column
	  private Long whoFind;//path ile gönderebiliriz.
	  
	  @Column(length = 40, nullable = false)
	  private String whoContacted;//yereldeki şirket sahibi olabilir.body ile gönderelim
	  
	  @Column(length = 250)
	  private String about;
	  
	  @Column(nullable = false)
	  private Date firstContactDate;
	  
	  @Column(length = 10, nullable = false)
	  private Boolean isMailSent;
	  
	  @Column(length = 10, nullable = false)
	  private Boolean isMsgSent;
	  
	  @Column(length = 10, nullable = false) 
	  private Boolean isOrder;
	  
	  @Column(length = 255)
	  private String note;
	 // @Column
	 // private Set<Emails> emails;
	  
	  @OneToOne(fetch = FetchType.LAZY)
	  private Lead lead;
	  
	  @OneToMany
	  private List<Orders> orders;
	  
	  @OneToMany
	  private List<Emails> emails; //???
	  
	  @Enumerated(EnumType.STRING)
	  private CompanyStatus companyStatus;
	 
	 
	  @Enumerated(EnumType.STRING)
	 private CompanyIndustry industry;
	 
	 
	  @Enumerated(EnumType.STRING)
	 private CompanyWhereWasFound companyWhereWasFound;
	 
	 
	  @Enumerated(EnumType.STRING)
	  private CompanyType companyType;
	  
	}
