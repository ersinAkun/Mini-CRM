package com.crm.service;


import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crm.domain.CompanyEmployees;
import com.crm.domain.Role;
import com.crm.domain.enums.RoleType;
import com.crm.exception.ConflictException;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;





@Service
public class CompanyEmployeesService {
	
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CompanyEmployeesRepository companyEmployeesRepository;
	
	
	private RoleService roleService;
	
	
	@Autowired
	public CompanyEmployeesService(@Lazy PasswordEncoder passwordEncoder,
			CompanyEmployeesRepository companyEmployeesRepository, RoleService roleService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.companyEmployeesRepository = companyEmployeesRepository;
		this.roleService = roleService;
	}

	public void createCompanyEmployees(CompanyEmployeesRequestDTO companyEmployeesRequestDTO) {
		if (companyEmployeesRepository.existsByEmail(companyEmployeesRequestDTO.getEmail())) {
			throw new ConflictException(
					String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, companyEmployeesRequestDTO.getEmail()));
		}
		

		Role role = roleService.findByType(RoleType.ROLE_USER);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		String encodedPassword = passwordEncoder.encode(companyEmployeesRequestDTO.getPassword());
		
		CompanyEmployees companyEmployees = new CompanyEmployees();
		
		companyEmployees.setPassword(encodedPassword);
		companyEmployees.setAddress(companyEmployeesRequestDTO.getAddress());
		companyEmployees.setCity(companyEmployeesRequestDTO.getCity());
		companyEmployees.setCountry(companyEmployeesRequestDTO.getCountry());
		companyEmployees.setEmail(companyEmployeesRequestDTO.getEmail());
		companyEmployees.setEmployeeDepartment(companyEmployeesRequestDTO.getEmployeeDepartment());
		companyEmployees.setFirstName(companyEmployeesRequestDTO.getFirstName());
		companyEmployees.setHasWhatsapp(companyEmployeesRequestDTO.getHasWhatsapp());
		companyEmployees.setJobTitle(companyEmployeesRequestDTO.getJobTitle());
		companyEmployees.setLastName(companyEmployeesRequestDTO.getLastName());
		companyEmployees.setNotes(companyEmployeesRequestDTO.getNotes());
		companyEmployees.setPhoneNumber(companyEmployeesRequestDTO.getPhoneNumber());
		companyEmployees.setSpeaks(companyEmployeesRequestDTO.getSpeaks());
		companyEmployees.setState(companyEmployeesRequestDTO.getState());
		companyEmployees.setBuiltIn(false);
		companyEmployees.setRoles(roles);
		companyEmployeesRepository.save(companyEmployees);
		

	}

		//security için
	public CompanyEmployees getEmployeeByEmail(String email) {
	
		CompanyEmployees companyEmployees  = companyEmployeesRepository.findByEmail(email).orElseThrow(()->
		new ResourceNotFoundException(String.format(ErrorMessage.EMPLOYEES_NOT_FOUND_MESSAGE,email))
		
				);
				
		return companyEmployees ;
	}


}
