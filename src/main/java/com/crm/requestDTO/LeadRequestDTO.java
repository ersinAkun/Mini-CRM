package com.crm.requestDTO;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.crm.domain.enums.Department;
import com.crm.domain.enums.LeadStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadRequestDTO {

	@Size(max = 50)
	@NotNull(message = "Please provide First name")
	private String firstName;

	@Size(max = 50)
	@NotNull(message = "Please provide Last name")
	private String lastName;

	@Email
	@NotNull(message = "Please provide email")
	private String email;


	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	@NotNull(message = "Please enter phone number")
	private String businessNumber;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	private String personelNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@NotNull(message = "Please provide the pick up time of the reservation")
	private Date contactedDate;

	@Size(max = 255)
	@NotNull(message = "Please provide address")
	private String address;

	@Size(max = 20)
	@NotNull(message = "Please provide city")
	private String city;

	@Size(max = 20)
	@NotNull(message = "Please provide state")
	private String state;

	@Size(max = 60)
	@NotNull(message = "Please provide country")
	private String country;

	private Boolean hasWhatsapp;

	@Size(max = 50)
	private String linked;

	@Size(max = 50)
	private String skype;

	@Size(max = 250)
	private String notes;

	@Size(max = 50)
	private String speaks;
	
	private String industry;

	@Enumerated(EnumType.STRING)
	private LeadStatus status;
	
	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

}
