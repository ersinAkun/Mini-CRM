package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.domain.Lead;
import com.crm.repository.LeadRepository;
import com.crm.requestDTO.LeadRequestDTO;

@Service
public class LeadService {
	
@Autowired
private LeadRepository leadRepository;

public void createLead( LeadRequestDTO leadRequestDTO){
	
	Lead lead= new Lead();
    lead.setFirstName(leadRequestDTO.getFirstName());
	lead.setLastName(leadRequestDTO.getLastName());
	lead.setEmail(leadRequestDTO.getEmail());
	lead.setBusinessNumber(leadRequestDTO.getBusinessNumber());
	lead.setPersonelNumber(leadRequestDTO.getPersonelNumber());
	lead.setContactedDate(leadRequestDTO.getContactedDate());
	lead.setAddress(leadRequestDTO.getAddress());
	lead.setCity(leadRequestDTO.getCity());
	lead.setState(leadRequestDTO.getState());
	lead.setCountry(leadRequestDTO.getCountry());
	lead.setLinked(leadRequestDTO.getLinked());
	lead.setSkype(leadRequestDTO.getSkype());
	lead.setNotes(leadRequestDTO.getNotes());
	lead.setSpeaks(leadRequestDTO.getSpeaks());
    lead.setStatus(leadRequestDTO.getStatus());
	lead.setEmployeeDepartment(leadRequestDTO.getEmployeeDepartment());
	lead.setHasWhatsapp(leadRequestDTO.getHasWhatsapp());
	lead.setIndustry(leadRequestDTO.getIndustry());
	//lead.setCompany(companyService.findCompanyById(cId));
	
	leadRepository.save(lead);	
}


// 
}
