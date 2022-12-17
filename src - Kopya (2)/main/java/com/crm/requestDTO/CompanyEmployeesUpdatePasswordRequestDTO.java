package com.crm.requestDTO;




import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEmployeesUpdatePasswordRequestDTO {

	@NotBlank(message="Please Provide Old Password")
	private String oldPassword;
	
	@NotBlank(message="Please Provide New Password")
	private String newPassword;
	
}
