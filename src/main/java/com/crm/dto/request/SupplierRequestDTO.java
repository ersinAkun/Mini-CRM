package com.crm.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SupplierRequestDTO {

	@Size(max = 50)
	@NotBlank(message = "Please provide Supplier Company name")
	private String name;

	@Size(max = 50)
	@NotBlank(message = "Please provide owner first name")
	private String ownerName;

	@Size(max = 50)
	@NotBlank(message = "Please provide owner last name")
	private String ownerLastName;

	@Size(max = 100)
	@NotBlank(message = "Please provide adress")
	private String address;

	@Size(max = 50)
	@NotBlank(message = "Please provide city")
	private String city;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	@NotNull(message = "Please enter phone number")
	private String phone;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	private String OwnerWhatsapp;

	@Size(max = 50)
	private String linkedPage;

	@Size(max = 50)
	private String webPage;

}
