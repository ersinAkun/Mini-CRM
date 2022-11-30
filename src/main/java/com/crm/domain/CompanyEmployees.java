package com.crm.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyEmployees {

	
	  private Long id;
	  
	  private String firstName;
	  
	  private String lastName;
	  
	  
	  private String email;
	  
	  private String jogTitle;
	  
	  private String phoneNumber;
	  
	  
	  private String address;
	  
	  private String city;
	  
	  private String country;
	  
	  private String state;
	  
	  private Boolean useWhatsapp;
	  
	  private String skype; 
	  
	  private String notes;
	  
	  private String speaks;
	  
	 
}
