package com.crm.service;

import com.crm.domain.Orders;
import com.crm.exception.ConflictException;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.CompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.crm.domain.Company;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    CompanyEmployeesService companyEmployeesService;
    @Autowired
    private CompanyEmployeesRepository companyEmployeesRepository;



//	public Company findCompanyById(Long id) {
//		
//		Company company= companyRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
//	return company;	
//	}


    public void saveCompany(CompanyRequestDTO companyRequestDTO, Long eId) {

        //girilen isimdeki company sayısına bakıyoruz
        Integer amountCompany = companyRepository.countByCompanyWithName(companyRequestDTO.getName().toUpperCase());

        if (amountCompany > 0) {
            throw new ConflictException(ErrorMessage.COMPANY_ALREADY_CREATED_MESSAGE);
        }

        Integer employee = companyEmployeesRepository.countById(eId);
        if (!(employee > 0)) {
            throw new ConflictException(ErrorMessage.EMPLOYEES_NOT_FOUND_MESSAGE);
        }

        Company company = new Company();

        LocalDateTime now = LocalDateTime.now();

        company.setName(companyRequestDTO.getName().toUpperCase(Locale.ROOT));
        company.setOwner(companyRequestDTO.getOwner());
        company.setAddress(companyRequestDTO.getAddress());
        company.setCountry(companyRequestDTO.getCountry());
        company.setCity(companyRequestDTO.getCity());
        company.setPhone(companyRequestDTO.getPhoneNumber());
        company.setLeadWhatsapp(companyRequestDTO.getLeadWhatsapp());
        company.setLastActivity(companyRequestDTO.getLastActivity());
        company.setLastActivityDate(companyRequestDTO.getLastActivityDate());
        company.setLinkedPage(companyRequestDTO.getLinkedPage());
        company.setTimeZone(companyRequestDTO.getTimeZone());
        company.setWebPage(companyRequestDTO.getWebPage());
        company.setRFQ(companyRequestDTO.getRfq());
        company.setWhoFind(eId);
        company.setAbout(companyRequestDTO.getAbout());
        company.setFirstContactDate(now);
        company.setIsMailSent(companyRequestDTO.getIsMailSent());
        company.setIsMsgSent(companyRequestDTO.getIsMsgSent());
        company.setIsOrder(companyRequestDTO.getIsOrder());
        company.setNote(companyRequestDTO.getNote());
        //company.setLead(companyRequestDTO.);
        //company.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
        // company.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
        company.setCompanyStatus(companyRequestDTO.getCompanyStatus());
        company.setIndustry(companyRequestDTO.getIndustry());
        company.setCompanyWhereWasFound(companyRequestDTO.getCompanyWhereWasFound());
        company.setCompanyType(companyRequestDTO.getCompanyType());

        companyRepository.save(company);


    }


    public void updateCompany(Long id, CompanyRequestDTO companyRequestDTO) {

        Company company = companyRepository.getReferenceById(id);

        Boolean existCompany = companyRepository.existsById(id);

        if (!existCompany) {
            throw new ConflictException(ErrorMessage.COMPANY_IS_NOT_EXIST_MESSAGE);
        }
        company.setName(companyRequestDTO.getName().toUpperCase(Locale.ROOT));
        company.setOwner(companyRequestDTO.getOwner());
        company.setAddress(companyRequestDTO.getAddress());
        company.setCountry(companyRequestDTO.getCountry());
        company.setCity(companyRequestDTO.getCity());
        company.setPhone(companyRequestDTO.getPhoneNumber());
        company.setLeadWhatsapp(companyRequestDTO.getLeadWhatsapp());
        company.setLastActivity(companyRequestDTO.getLastActivity());
        company.setLastActivityDate(companyRequestDTO.getLastActivityDate());
        company.setLinkedPage(companyRequestDTO.getLinkedPage());
        company.setTimeZone(companyRequestDTO.getTimeZone());
        company.setWebPage(companyRequestDTO.getWebPage());
        company.setRFQ(companyRequestDTO.getRfq());
       // company.setWhoFind(companyRequestDTO.get);
        company.setAbout(companyRequestDTO.getAbout());
        company.setIsMailSent(companyRequestDTO.getIsMailSent());
        company.setIsMsgSent(companyRequestDTO.getIsMsgSent());
        company.setIsOrder(companyRequestDTO.getIsOrder());
        company.setNote(companyRequestDTO.getNote());
        //company.setLead(companyRequestDTO.);
        //company.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
        // company.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
        company.setCompanyStatus(companyRequestDTO.getCompanyStatus());
        company.setIndustry(companyRequestDTO.getIndustry());
        company.setCompanyWhereWasFound(companyRequestDTO.getCompanyWhereWasFound());
        company.setCompanyType(companyRequestDTO.getCompanyType());

        companyRepository.save(company);






    }

    public CompanyResponseDTO getCompanyById(Long id) {
        Company company =  companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        CompanyResponseDTO companyResponseDTO= new CompanyResponseDTO();

        companyResponseDTO.setName(company.getName().toUpperCase(Locale.ROOT));
        companyResponseDTO.setOwner(company.getOwner());
        companyResponseDTO.setAddress(company.getAddress());
        companyResponseDTO.setCountry(company.getCountry());
        companyResponseDTO.setCity(company.getCity());
        companyResponseDTO.setPhoneNumber(company.getPhone());
        companyResponseDTO.setLeadWhatsapp(company.getLeadWhatsapp());
        companyResponseDTO.setLastActivity(company.getLastActivity());
        companyResponseDTO.setLastActivityDate(company.getLastActivityDate());
        companyResponseDTO.setLinkedPage(company.getLinkedPage());
        companyResponseDTO.setTimeZone(company.getTimeZone());
        companyResponseDTO.setWebPage(company.getWebPage());
        companyResponseDTO.setRfq(company.getRFQ());
        companyResponseDTO.setWhoFind(company.getWhoFind());
        companyResponseDTO.setAbout(company.getAbout());
        companyResponseDTO.setFirstContactDate(company.getFirstContactDate());
        companyResponseDTO.setIsMailSent(company.getIsMailSent());
        companyResponseDTO.setIsMsgSent(company.getIsMsgSent());
        companyResponseDTO.setIsOrder(company.getIsOrder());
        companyResponseDTO.setNote(company.getNote());
        //companyResponseDTO.setLead(companyRequestDTO.);
        //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
        // companyResponseDTO.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
        companyResponseDTO.setCompanyStatus(company.getCompanyStatus());
        companyResponseDTO.setIndustry(company.getIndustry());
        companyResponseDTO.setCompanyWhereWasFound(company.getCompanyWhereWasFound());
        companyResponseDTO.setCompanyType(company.getCompanyType());

        return companyResponseDTO;
    }
}























