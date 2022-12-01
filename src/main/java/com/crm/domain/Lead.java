package com.crm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.crm.domain.enums.Department;
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
@Table(name = "tbl_Lead")

public class Lead {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	
	@OneToOne
	private Company company;//ilişkiden gelsin.
	@Column
	private String industry;//bunu company üzerinden çekip set edebiliriz.
	
	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;
	@Column
	private String businessNumber;
	@Column
	private String personelNumber;
	@Column
	private Date contactedDate;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String country;
	@Column
	private Boolean hasWhatsapp;
	@Column
	private String Linked; 
	@Column
	private String skype;
	@Column
	private String notes;
	@Column
	private String speaks;
		
	@Enumerated(EnumType.STRING)
	private LeadStatus status;
	
	
}
