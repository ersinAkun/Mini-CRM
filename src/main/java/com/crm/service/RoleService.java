package com.crm.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.domain.Role;
import com.crm.domain.enums.RoleType;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByType(RoleType roleType) {
		Role role= roleRepository.findByType(roleType).orElseThrow(()->
					new ResourceNotFoundException(String.format
					(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, roleType.name())));
		
		return role;
	}
}
