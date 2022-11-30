package com.crm.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_company")
public class Company {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
	
  private String name;
  
  
  private String owner;
  
  
  private String address;
  
  
  private String country;
  
  
  private String city;
  
    
  private String phone;
  
  
  private String leadWhatsapp;
 
   
  private String lastActivity;
  
  
  private Date lastActivityDate;
  
  
  private String linkedPage;
  
  
  private String timeZone;
  
  
  private String webPage;
  
  
  private String RFQ;
  
  
  private String whoFind;
   
  private String whoContacted;
  
  private String about; 
  
  
  private Date firstContactDate;
  
  
  private Boolean isMailSent;
  
  
  private Boolean isMsgSent;
  
  
  private Boolean isOrder;
  
  private String note;
  
  @OneToMany//defaultta lazy.
  private List<Emails> emails;
  
  @OneToOne(fetch = FetchType.LAZY)
  private Lead lead;
  
  @OneToMany
  private List<Orders> orders;
  
  @ManyToMany
  CompanyEmployees companyEmployees;
  
 @Enumerated 
  private CompanyStatus companyStatus;
 
 
 @Enumerated
 private CompanyIndustry industry;
 
 
 @Enumerated
 private CompanyWhereWasFound information;
 
 
 @Enumerated
  private CompanyType companyType;
  
}
//emin





//ersin deneme1
