package com.crm.domain;

import java.util.Date;

import javax.persistence.*;

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
	private Long id;

	@Column(length = 30)
	private String firstName;

	@Column(length = 30)
	private String lastName;
	
	@Column(length = 50,unique = true)// nullable = false, 
	private String email;

	@OneToOne
	private Company company;// ilişkiden gelsin.

	@Column(length = 50)
	private String industry;// bunu company üzerinden çekip set edebiliriz.

	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

	@Column(length = 14)
	private String businessNumber;
	
	@Column(length = 14)
	private String personelNumber;

	@Column
	private Date contactedDate;

	@Column(length = 50)
	private String address;

	@Column(length = 30)
	private String city;
	
	@Column(length = 30)
	private String state;
	
	@Column(length = 60)
	private String country;
	
	@Column(length = 14)
	private Boolean hasWhatsapp;
	
	@Column(length = 50)
	private String Linked;
	
	@Column(length = 30)
	private String skype;
	
	@Column
	private String notes;
	
	@Column(length = 30)
	private String speaks;

	@Enumerated(EnumType.STRING)
	private LeadStatus status;

}
