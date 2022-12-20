package com.crm.responseDTO;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.crm.domain.Company;
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
public class LeadResponseDTO {



	private String firstName;


	private String lastName;

	private String email;


	private Company company;

	private String industry;


	private String businessNumber;

	private String personelNumber;


	private Date contactedDate;


	private String address;


	private String city;


	private String state;


	private String country;


	private Boolean hasWhatsapp;


	private String Linked;


	private String skype;


	private String notes;


	private String speaks;

	@Enumerated(EnumType.STRING)
	private LeadStatus status;

	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

}