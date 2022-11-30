package com.crm.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.crm.domain.Enums;
import com.crm.domain.enums.LeadStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class LeadDTO {
private long id;
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String companyName;
	
	private String industry;
	
	private String jobTitle;
	
	private String businessNumber;
	
	private String personelNumber;
	
	private String businessEmail;
	
	private Date contactedDate;

	private String address;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Boolean useWhatsapp;
	
	private String inLinked; 
	
	private String skype;
	
	private String notes;
	
	private String speaks;
	
	
	@Enumerated
	
	private LeadStatus status;
	
}
