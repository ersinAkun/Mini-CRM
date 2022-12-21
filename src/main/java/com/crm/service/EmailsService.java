package com.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crm.domain.Emails;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyRepository;
import com.crm.repository.EmailsRepository;
import com.crm.responseDTO.EmailsResponseDTO;

@Service
public class EmailsService {



	//******CELEBI*******GET BY EMAIL**********************
	public EmailsResponseDTO getEmailById(Long id) {
		
		Emails emails= emailsRepository.findById(id).orElseThrow(()->
		   new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		
		EmailsResponseDTO emailsResponseDTO= new EmailsResponseDTO();
		
		emailsResponseDTO.setEmail(emails.getEmail());
		emailsResponseDTO.setId(emails.getId());
		emailsResponseDTO.setCompany_id(emails.getCompany().getId());
		emailsResponseDTO.setCompany_name(emails.getCompany().getName());
		
		return emailsResponseDTO;
	}

    @Autowired
    private EmailsRepository emailsRepository;

    // @Autowired
    // private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    //*****CELEBI********CREATE EMAILS**********************
   /* public void createEmails(Long cId,EmailsRequestDTO emailsRequestDTO) {

        Company company= companyService.findCompanyById(cId);

        Emails emails= new Emails();

        emails.setEmail(emailsRequestDTO.getEmail());
        emails.setCompany(company);

        emailsRepository.save(emails);
    }*/

    //*****GET BY ID EMAIL POJO*************
    public Emails getEmail(Long id) {
        Emails email = emailsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.EMAIL_NOT_FOUND_MESSAGE, id))
        );
        return email;
    }



   

	
	//******CELEBI*******GET ALL**********************
	public List<EmailsResponseDTO> getAllEmails() {
		List<Emails> emails = emailsRepository.findAll();
		
		List<EmailsResponseDTO> emailsResponseDTOs =new ArrayList<>();
		
		for (Emails emailsAll : emails) {
			EmailsResponseDTO emailsResponseDTO=new EmailsResponseDTO();
			emailsResponseDTO.setEmail(emailsAll.getEmail());
			emailsResponseDTO.setId(emailsAll.getId());
			emailsResponseDTO.setCompany_id(emailsAll.getCompany().getId());
			emailsResponseDTO.setCompany_name(emailsAll.getCompany().getName());
			
			emailsResponseDTOs.add(emailsResponseDTO);
		}
		
		return emailsResponseDTOs;
	}
	



    //******CELEBI*******GET PAGE EMAIL**********************
    public Page<EmailsResponseDTO> getEmailsPage(Pageable pageable) {

        Page<Emails> emailsPage = emailsRepository.findAll(pageable);

        Page<EmailsResponseDTO> emailDTOPage = emailsPage.map(new Function<Emails, EmailsResponseDTO>() {

        	@Override
			public EmailsResponseDTO apply(Emails emails) {
				EmailsResponseDTO emailsResponseDTO= new EmailsResponseDTO();
				
				emailsResponseDTO.setEmail(emails.getEmail());
				emailsResponseDTO.setId(emails.getId());
				emailsResponseDTO.setCompany_id(emails.getCompany().getId());
				emailsResponseDTO.setCompany_name(emails.getCompany().getName());
				return emailsResponseDTO;
			}
			
		}); 
		
		
		return emailDTOPage;
	}
    

    //******CELEBI*******GET BY ID UPDATE EMAIL**********************
   /* public void updateEmail(Long id, Long cId, EmailsRequestDTO emailsRequestDTO) {
        Emails email=getEmail(id);

        Company company= companyService.findCompanyById(cId);

        @SuppressWarnings("unlikely-arg-type")
        boolean emailExist  = emailsRepository.findAll().contains(emailsRequestDTO.getEmail());

        if(emailExist && ! emailsRequestDTO.getEmail().equals(email.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE,emailsRequestDTO.getEmail()));
        }

        email.setEmail(emailsRequestDTO.getEmail());
        email.setCompany(company);

        emailsRepository.save(email);
    }*/


    //******CELEBI*******GET DELETE EMAIL**********************
    public void removeEmailById(Long id) {


        emailsRepository.deleteById(id);
    }

    //emailleri company id ye göre filitreleyip Strinring degere dönüştürür **SELİM**
    public String getEmailsTypeString(Long id) {
        List<String> emailsAdress = emailsRepository.findByEmailsNameWithCompanyId(id);

        String emails = String.join(", ", emailsAdress);

        return emails;
    }

}