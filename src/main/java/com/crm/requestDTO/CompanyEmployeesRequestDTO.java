package com.crm.requestDTO;


import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.crm.domain.enums.Department;
import com.crm.domain.enums.Department;
import com.crm.domain.enums.Department;
import com.crm.domain.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEmployeesRequestDTO {

	
	
	@Size(max = 50)
	@NotNull
	@NotBlank(message = "Please provide First name")
	private String firstName;
	
	@Size(max = 50)
	@NotNull
	@NotBlank(message = "Please provide Last name")
	private String lastName;
	
	@Size(max = 50)
	@Email
	//@NotNull(message = "Please provide email")
	private String email;
	
	@Size(max = 30)
	@NotNull(message = "Please provide password")
	private String password;
	
	
	@Size(max = 50)
	@NotBlank(message = "Please provide job Title")
	private String jobTitle;
	
	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 10, max = 14, message = "Please enter valid phone number")
	//@NotNull(message = "Please enter phone number")
	private String phoneNumber;
	
	@Size(max = 255)
	@NotNull(message = "Please provide address")
	@NotBlank
	private String address;
	
	@Size(max = 20)
	@NotNull(message = "Please provide city")
	@NotBlank
	private String city;
	
	@Size(max = 50)
	@NotNull(message = "Please provide country")
	@NotBlank
	private String country;
	
	@Size(max = 20)
	@NotNull(message = "Please provide state")
	@NotBlank
	private String state;
	
	private Boolean hasWhatsapp;
	
	@Size(max = 250)
	private String notes;
	
	@Size(max = 50)
	@NotBlank
	private String speaks;
	
	
	private Boolean builtIn;

	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

	private Set<String> roles ;
}
