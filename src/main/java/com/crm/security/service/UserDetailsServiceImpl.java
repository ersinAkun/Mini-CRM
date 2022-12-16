package com.crm.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.crm.domain.CompanyEmployees;
import com.crm.service.CompanyEmployeesService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CompanyEmployeesService companyEmployeesService;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String  email) throws UsernameNotFoundException {
		
		CompanyEmployees companyEmployees =  companyEmployeesService.getEmployeeByEmail(email);
		 return UserDetailsImpl.build(companyEmployees);
	}

}
