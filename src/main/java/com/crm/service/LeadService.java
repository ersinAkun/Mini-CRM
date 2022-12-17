package com.crm.service;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.domain.Lead;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.LeadRepository;
import com.crm.requestDTO.LeadRequestDTO;
import com.crm.responseDTO.LeadResponseDTO;


@Service
public class LeadService {
	
@Autowired
private LeadRepository leadRepository;



// ********  CREATE LEAD  *************/



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



//********* GET LEAD ***************/

public LeadResponseDTO getLeadById(Long id) {
	Lead lead= leadRepository.findById(id).orElseThrow(()->
	   new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
	
	LeadResponseDTO leadResponseDTO= new LeadResponseDTO();
	
	leadResponseDTO.setFirstName(lead.getFirstName());
	leadResponseDTO.setLastName(lead.getLastName());
	leadResponseDTO.setEmail(lead.getEmail());
	//leadResponseDTO.setCompany(lead.getCompany());
	leadResponseDTO.setIndustry(lead.getIndustry());
	leadResponseDTO.setBusinessNumber(lead.getBusinessNumber());
	leadResponseDTO.setPersonelNumber(lead.getPersonelNumber());
	leadResponseDTO.setContactedDate(lead.getContactedDate());
	leadResponseDTO.setAddress(lead.getAddress());
	leadResponseDTO.setCity(lead.getCity());
	leadResponseDTO.setState(lead.getState());
	leadResponseDTO.setCountry(lead.getCountry());
	leadResponseDTO.setHasWhatsapp(lead.getHasWhatsapp());
	leadResponseDTO.setLinked(lead.getLinked());
	leadResponseDTO.setSkype(lead.getSkype());
	leadResponseDTO.setNotes(lead.getNotes());
	leadResponseDTO.setSpeaks(lead.getSpeaks());
	leadResponseDTO.setStatus(lead.getStatus());
	leadResponseDTO.setEmployeeDepartment(lead.getEmployeeDepartment());
	return leadResponseDTO;
}

//******************  GET ALL LEAD  *****************/

	public List<LeadResponseDTO> getAllLead() {

		List<Lead> leadList = leadRepository.findAll();

		List<LeadResponseDTO> dtoList = new ArrayList<>();

		for (Lead lead : leadList) {

			LeadResponseDTO leadResponseDTO = new LeadResponseDTO();

			leadResponseDTO.setFirstName(lead.getFirstName());
			leadResponseDTO.setLastName(lead.getLastName());
			leadResponseDTO.setEmail(lead.getEmail());
			//leadResponseDTO.setCompany(lead.getCompany());
			leadResponseDTO.setIndustry(lead.getIndustry());
			leadResponseDTO.setBusinessNumber(lead.getBusinessNumber());
			leadResponseDTO.setPersonelNumber(lead.getPersonelNumber());
			leadResponseDTO.setContactedDate(lead.getContactedDate());
			leadResponseDTO.setAddress(lead.getAddress());
			leadResponseDTO.setCity(lead.getCity());
			leadResponseDTO.setState(lead.getState());
			leadResponseDTO.setCountry(lead.getCountry());
			leadResponseDTO.setHasWhatsapp(lead.getHasWhatsapp());
			leadResponseDTO.setLinked(lead.getLinked());
			leadResponseDTO.setSkype(lead.getSkype());
			leadResponseDTO.setNotes(lead.getNotes());
			leadResponseDTO.setSpeaks(lead.getSpeaks());
			leadResponseDTO.setStatus(lead.getStatus());
			leadResponseDTO.setEmployeeDepartment(lead.getEmployeeDepartment());
			dtoList.add(leadResponseDTO);

		}

		return dtoList;

	
}
// ***************  UPDATE LEAD  **********************/
	
	public void updateLead( Long id, LeadRequestDTO leadRequestDTO) {
		
		Lead lead= leadRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.LEAD_UPDATE_RESPONSE_MESSAGE, id)));;
		lead.setFirstName(leadRequestDTO.getFirstName());
		lead.setLastName(leadRequestDTO.getLastName());
		lead.setEmail(leadRequestDTO.getEmail());
		lead.setIndustry(leadRequestDTO.getIndustry());
		lead.setEmployeeDepartment(leadRequestDTO.getEmployeeDepartment());
		lead.setBusinessNumber(leadRequestDTO.getBusinessNumber());
		lead.setPersonelNumber(leadRequestDTO.getPersonelNumber());
		lead.setAddress(leadRequestDTO.getAddress());
		lead.setHasWhatsapp(leadRequestDTO.getHasWhatsapp());
		lead.setLinked(leadRequestDTO.getLinked());
		lead.setSkype(leadRequestDTO.getSkype());
		lead.setNotes(leadRequestDTO.getNotes());
	    lead.setStatus(leadRequestDTO.getStatus());
	    lead.setCity(leadRequestDTO.getCity());
	    lead.setState(leadRequestDTO.getState());
	    lead.setCountry(leadRequestDTO.getCountry());
	    lead.setSpeaks(leadRequestDTO.getSpeaks());
	    lead.setContactedDate(leadRequestDTO.getContactedDate());

	
		leadRepository.save(lead);
 
}

// ***********  PAGEABLE LEAD  **********************/
	
	public Page<LeadResponseDTO> findAllWithPage(Pageable pageable) {
		
		Page<Lead> leadPage = leadRepository.findAll(pageable);
		Page<LeadResponseDTO> responsePage = leadPage.map(new Function<Lead, LeadResponseDTO>() {
			
			
			@Override
			public LeadResponseDTO apply(Lead lead) {
				
				LeadResponseDTO leadResponseDTO = new LeadResponseDTO();
				
				leadResponseDTO.setFirstName(lead.getFirstName());
				leadResponseDTO.setLastName(lead.getLastName());
				leadResponseDTO.setEmail(lead.getEmail());
				//leadResponseDTO.setCompany(lead.getCompany());
				leadResponseDTO.setIndustry(lead.getIndustry());
				leadResponseDTO.setBusinessNumber(lead.getBusinessNumber());
				leadResponseDTO.setPersonelNumber(lead.getPersonelNumber());
				leadResponseDTO.setContactedDate(lead.getContactedDate());
				leadResponseDTO.setAddress(lead.getAddress());
				leadResponseDTO.setCity(lead.getCity());
				leadResponseDTO.setState(lead.getState());
				leadResponseDTO.setCountry(lead.getCountry());
				leadResponseDTO.setHasWhatsapp(lead.getHasWhatsapp());
				leadResponseDTO.setLinked(lead.getLinked());
				leadResponseDTO.setSkype(lead.getSkype());
				leadResponseDTO.setNotes(lead.getNotes());
				leadResponseDTO.setSpeaks(lead.getSpeaks());
				leadResponseDTO.setStatus(lead.getStatus());
				leadResponseDTO.setEmployeeDepartment(lead.getEmployeeDepartment());
				return leadResponseDTO;
				
			}
		});
		
		return responsePage;
	
	
	}

	//******************  DELETE LEAD  ******************/

	public void deleteLeadById(Long id) {
		Lead lead= leadRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		leadRepository.delete(lead);
	}
	}
