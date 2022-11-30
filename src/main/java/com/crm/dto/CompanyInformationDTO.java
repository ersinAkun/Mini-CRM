package com.crm.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

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
public class CompanyInformationDTO {
	private long id;
	
	  private String companyName;
	  
	  
	  private String companyOwner;
	  
	  
	  private String address;
	  
	  
	  private String country;
	  
	  
	  private String city;
	  
	    
	  private String phone;
	  
	  
	  private String contactWhatsapp;
	  
	  
	  private String emails;
	  
	  
	  private String lastActivity;
	  
	  
	  private String lastActivityDate;
	  
	  
	  private String linkedPage;
	  
	  
	  private String timeZone;
	  
	  
	  private String webPage;
	  
	  
	  private String RFQ;
	  
	  
	  private String middleman;
	  
	  
	  private String whoContacted;
	  
	  private String about; 
	  
	  
	  private Date firstContactDate;
	  
	  
	  private Boolean sendEmail;
	  
	  
	  private Boolean sendMessage;
	  
	  
	  private Boolean orderPlacement;
	  
	  private String note;
	  
	  
	  
	 @Enumerated 
	  private CompanyStatus status;
	 
	 
	 @Enumerated
	 private CompanyIndustry industry;
	 
	 
	 @Enumerated
	 private CompanyWhereWasFound information;
	 
	 
	 @Enumerated
	  private CompanyType corporation;
	  
}
