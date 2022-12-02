package com.crm.requestDTO;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.domain.enums.CompanyWhereWasFound;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

	@Size(max = 50)
	@NotBlank(message = "Please provide First name")
	private String name;

	@Size(max = 50)
	@NotBlank(message = "Please provide owner name")
	private String owner;

	@Size(max = 100)
	@NotBlank(message = "Please provide address")
	private String address;

	@Size(max = 50)
	@NotBlank(message = "Please provide country")
	private String country;

	@Size(max = 50)
	@NotBlank(message = "Please provide city")
	private String city;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	@NotNull(message = "Please enter phone number")
	private String phoneNumber;

	@Size(max = 50)
	private String leadWhatsapp;

	@Size(max = 150)
	private String lastActivity;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date lastActivityDate;

	@Size(max = 50)
	private String linkedPage;

	@Size(max = 10)
	@NotBlank(message = "Please provide time zone")
	private String timeZone;

	@Size(max = 50)
	private String webPage;

	@Size(max = 150)
	@NotBlank(message = "Please provide RFQ")
	private String RFQ; 

	@Size(max = 50)
	private Long whoFind;// path ile gönderebiliriz.

	@Size(max = 50)
	@NotBlank(message = "Please provide name")
	private String whoContacted;// yereldeki şirket sahibi olabilir.body ile gönderelim

	@Size(max = 250)
	private String about;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@NotNull(message = "Please provide first Contact Date")
	private Date firstContactDate;

	@Size(max = 10)
	@NotNull(message = "Please provide information")
	private Boolean isMailSent;

	@Size(max = 10)
	@NotBlank(message = "Please provide information")
	private Boolean isMsgSent;

	@Size(max = 10)
	@NotBlank(message = "Please provide information")
	private Boolean isOrder;

	@Size(max = 255)
	private String note;

	

	@Enumerated(EnumType.STRING)
	private CompanyStatus companyStatus;

	@Enumerated(EnumType.STRING)
	private CompanyIndustry industry;

	@Enumerated(EnumType.STRING)
	private CompanyWhereWasFound companyWhereWasFound;

	@Enumerated(EnumType.STRING)
	private CompanyType companyType;

}