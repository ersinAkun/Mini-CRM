package com.crm.responseDTO;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.crm.domain.Company;
import com.crm.domain.Role;
import com.crm.domain.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEmployeesResponseDTO {

	
	private Long id;
	
	private String firstName;
	
	
	private String lastName;
	

	private String email;
	

	private String jobTitle;
	
	
	private String phoneNumber;
	

	private String address;
	
	
	private String city;
	
	
	private String country;
	
	
	private String state;
	
	private Boolean hasWhatsapp;
	

	private String notes;
	

	private String speaks;
	
	
	private Boolean builtIn;

	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

	
	private List<String> foundedCompanies = new ArrayList<>();

	private  Set<String> roles;
	
	public void setRoles(Set<Role> roles) {
		Set<String> roleStr=new HashSet<>();
		roles.forEach(r->{
			roleStr.add(r.getType().getName()); //Administrator veya Customer gozukecek
		});
		this.roles = roleStr;
	}
	
	
	
	
	
	
}
