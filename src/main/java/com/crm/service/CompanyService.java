package com.crm.service;

import com.crm.domain.Orders;
import com.crm.exception.ConflictException;
import com.crm.requestDTO.CompanyRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.crm.domain.Company;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyService {
@Autowired
private CompanyRepository companyRepository;


//	public Company findCompanyById(Long id) {
//		
//		Company company= companyRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
//	return company;	
//	}


    public void saveCompany(CompanyRequestDTO companyRequestDTO) {

        //girilen isimdeki company sayısına bakıyoruz
        Integer amountCompany=companyRepository.countByCompanyWithName(companyRequestDTO.getName());

        if (amountCompany>0){
            throw new ConflictException(ErrorMessage.COMPANY_ALREADY_CREATED_MESSAGE);
        }

        Company company= new Company();

        LocalDateTime now = LocalDateTime.now();

        company.setName(companyRequestDTO.getName());
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
        company.setRFQ(companyRequestDTO.getRFQ());
        //company.setWhoFind(companyRequestDTO.getWhoFind());
        company.setWhoContacted(companyRequestDTO.getWhoContacted());
        company.setAbout(companyRequestDTO.getAbout());
        company.setFirstContactDate(now);
        company.setIsMailSent(companyRequestDTO.getIsMailSent());
        company.setIsMsgSent(companyRequestDTO.getIsMsgSent());
        company.setIsOrder(companyRequestDTO.getIsOrder());
        company.setNote(companyRequestDTO.getNote());
        //company.setLead(companyRequestDTO.);
        //company.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
        //company.setEmails
        company.setCompanyStatus(companyRequestDTO.getCompanyStatus());
        company.setIndustry(companyRequestDTO.getIndustry());
        company.setCompanyWhereWasFound(companyRequestDTO.getCompanyWhereWasFound());
        company.setCompanyType(companyRequestDTO.getCompanyType());

        companyRepository.save(company);



    }


    public void updateCompany(Long id, CompanyRequestDTO companyRequestDTO) {


    }
}
