package com.crm.service;

import com.crm.domain.CompanyEmployees;
import com.crm.domain.Orders;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.exception.ConflictException;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.CompanyEmployeesResponseDTO;
import com.crm.responseDTO.CompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.crm.domain.Company;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    CompanyEmployeesService companyEmployeesService;
    @Autowired
    private CompanyEmployeesRepository companyEmployeesRepository;


	public Company findCompanyById(Long id) {

		Company company= companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));;
	return company;
	}


    //************************* CREATE **************************************
    public void saveCompany(CompanyRequestDTO companyRequestDTO, Long eId) {

        //girilen isimdeki company sayısına bakıyoruz
        Integer amountCompany = companyRepository.countByCompanyWithName(companyRequestDTO.getName().toUpperCase());

        if (amountCompany > 0) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_ALREADY_CREATED_MESSAGE,companyRequestDTO.getName()));
        }

        Integer employee = companyEmployeesRepository.countById(eId);
        if (!(employee > 0)) {
            throw new ConflictException(String.format(ErrorMessage.EMPLOYEES_NOT_FOUND_BY_ID_MESSAGE,eId));
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

//************************* UPDATE **************************************

    public void updateCompany(Long id, CompanyRequestDTO companyRequestDTO) {

        Company company = companyRepository.getReferenceById(id);

        Boolean existCompany = companyRepository.existsById(id);

        if (!existCompany) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_IS_NOT_EXIST_MESSAGE,companyRequestDTO.getName()));
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


    //************************* GET *****************************************
    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

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
        companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(company.getWhoFind()));
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


    //************************* DELETE **************************************
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        companyRepository.deleteById(id);
    }


    //************************* GET ALL *************************************
    public List<CompanyResponseDTO> getAllCompanies() {
        List<Company> company = companyRepository.findAll();

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Company companies : company) {

            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

            companyResponseDTO.setName(companies.getName().toUpperCase(Locale.ROOT));
            companyResponseDTO.setOwner(companies.getOwner());
            companyResponseDTO.setAddress(companies.getAddress());
            companyResponseDTO.setCountry(companies.getCountry());
            companyResponseDTO.setCity(companies.getCity());
            companyResponseDTO.setPhoneNumber(companies.getPhone());
            companyResponseDTO.setLeadWhatsapp(companies.getLeadWhatsapp());
            companyResponseDTO.setLastActivity(companies.getLastActivity());
            companyResponseDTO.setLastActivityDate(companies.getLastActivityDate());
            companyResponseDTO.setLinkedPage(companies.getLinkedPage());
            companyResponseDTO.setTimeZone(companies.getTimeZone());
            companyResponseDTO.setWebPage(companies.getWebPage());
            companyResponseDTO.setRfq(companies.getRFQ());
            companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(companies.getWhoFind()));
            companyResponseDTO.setAbout(companies.getAbout());
            companyResponseDTO.setFirstContactDate(companies.getFirstContactDate());
            companyResponseDTO.setIsMailSent(companies.getIsMailSent());
            companyResponseDTO.setIsMsgSent(companies.getIsMsgSent());
            companyResponseDTO.setIsOrder(companies.getIsOrder());
            companyResponseDTO.setNote(companies.getNote());
            //companyResponseDTO.setLead(companyRequestDTO.);
            //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
            // companyResponseDTO.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
            companyResponseDTO.setCompanyStatus(companies.getCompanyStatus());
            companyResponseDTO.setIndustry(companies.getIndustry());
            companyResponseDTO.setCompanyWhereWasFound(companies.getCompanyWhereWasFound());
            companyResponseDTO.setCompanyType(companies.getCompanyType());

            companyResponseDTOs.add(companyResponseDTO);
        }
        return companyResponseDTOs;
    }


    //************************* GET PAGEABLE ********************************
    public Page<CompanyResponseDTO> getCompaniesPage(Pageable pageable) {

        Page<Company> companiesPage = companyRepository.findAll(pageable);

        Page<CompanyResponseDTO> responsePage = companiesPage.map(new Function<Company, CompanyResponseDTO>() {

            @Override
            public CompanyResponseDTO apply(Company company) {
                CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

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
                companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(company.getWhoFind()));
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

        });

        return responsePage;


    }


    public List<CompanyResponseDTO> getCompaniesByCompanyStatus(CompanyStatus companyStatus) {
        List<Company> company = companyRepository.findCompaniesByCompanyStatus(companyStatus);

        if (company.size()==0){
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_STATUS_IS_NOT_EXIST_MESSAGE,companyStatus));
        }

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Company companies : company) {

            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

            companyResponseDTO.setName(companies.getName().toUpperCase(Locale.ROOT));
            companyResponseDTO.setOwner(companies.getOwner());
            companyResponseDTO.setAddress(companies.getAddress());
            companyResponseDTO.setCountry(companies.getCountry());
            companyResponseDTO.setCity(companies.getCity());
            companyResponseDTO.setPhoneNumber(companies.getPhone());
            companyResponseDTO.setLeadWhatsapp(companies.getLeadWhatsapp());
            companyResponseDTO.setLastActivity(companies.getLastActivity());
            companyResponseDTO.setLastActivityDate(companies.getLastActivityDate());
            companyResponseDTO.setLinkedPage(companies.getLinkedPage());
            companyResponseDTO.setTimeZone(companies.getTimeZone());
            companyResponseDTO.setWebPage(companies.getWebPage());
            companyResponseDTO.setRfq(companies.getRFQ());
            companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(companies.getWhoFind()));
            companyResponseDTO.setAbout(companies.getAbout());
            companyResponseDTO.setFirstContactDate(companies.getFirstContactDate());
            companyResponseDTO.setIsMailSent(companies.getIsMailSent());
            companyResponseDTO.setIsMsgSent(companies.getIsMsgSent());
            companyResponseDTO.setIsOrder(companies.getIsOrder());
            companyResponseDTO.setNote(companies.getNote());
            //companyResponseDTO.setLead(companyRequestDTO.);
            //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
            // companyResponseDTO.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
            companyResponseDTO.setCompanyStatus(companies.getCompanyStatus());
            companyResponseDTO.setIndustry(companies.getIndustry());
            companyResponseDTO.setCompanyWhereWasFound(companies.getCompanyWhereWasFound());
            companyResponseDTO.setCompanyType(companies.getCompanyType());

            companyResponseDTOs.add(companyResponseDTO);
        }
        return companyResponseDTOs;
    }

    public List<CompanyResponseDTO> getCompaniesByIndustry(CompanyIndustry industry) {
        List<Company> company = companyRepository.findCompaniesByIndustry(industry);

        if (company.size()==0){
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_INDUSTRY_IS_NOT_EXIST_MESSAGE,industry));
        }

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Company companies : company) {

            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

            companyResponseDTO.setName(companies.getName().toUpperCase(Locale.ROOT));
            companyResponseDTO.setOwner(companies.getOwner());
            companyResponseDTO.setAddress(companies.getAddress());
            companyResponseDTO.setCountry(companies.getCountry());
            companyResponseDTO.setCity(companies.getCity());
            companyResponseDTO.setPhoneNumber(companies.getPhone());
            companyResponseDTO.setLeadWhatsapp(companies.getLeadWhatsapp());
            companyResponseDTO.setLastActivity(companies.getLastActivity());
            companyResponseDTO.setLastActivityDate(companies.getLastActivityDate());
            companyResponseDTO.setLinkedPage(companies.getLinkedPage());
            companyResponseDTO.setTimeZone(companies.getTimeZone());
            companyResponseDTO.setWebPage(companies.getWebPage());
            companyResponseDTO.setRfq(companies.getRFQ());
            companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(companies.getWhoFind()));
            companyResponseDTO.setAbout(companies.getAbout());
            companyResponseDTO.setFirstContactDate(companies.getFirstContactDate());
            companyResponseDTO.setIsMailSent(companies.getIsMailSent());
            companyResponseDTO.setIsMsgSent(companies.getIsMsgSent());
            companyResponseDTO.setIsOrder(companies.getIsOrder());
            companyResponseDTO.setNote(companies.getNote());
            //companyResponseDTO.setLead(companyRequestDTO.);
            //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
            // companyResponseDTO.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
            companyResponseDTO.setCompanyStatus(companies.getCompanyStatus());
            companyResponseDTO.setIndustry(companies.getIndustry());
            companyResponseDTO.setCompanyWhereWasFound(companies.getCompanyWhereWasFound());
            companyResponseDTO.setCompanyType(companies.getCompanyType());

            companyResponseDTOs.add(companyResponseDTO);
        }
        return companyResponseDTOs;






    }

    public List<CompanyResponseDTO> getCompaniesByCompanyType(CompanyType companyType) {
        List<Company> company = companyRepository.findCompaniesByCompanyType(companyType);
        if (company.size()==0){
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_TYPE_IS_NOT_EXIST_MESSAGE,companyType));
        }
        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Company companies : company) {

            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

            companyResponseDTO.setName(companies.getName().toUpperCase(Locale.ROOT));
            companyResponseDTO.setOwner(companies.getOwner());
            companyResponseDTO.setAddress(companies.getAddress());
            companyResponseDTO.setCountry(companies.getCountry());
            companyResponseDTO.setCity(companies.getCity());
            companyResponseDTO.setPhoneNumber(companies.getPhone());
            companyResponseDTO.setLeadWhatsapp(companies.getLeadWhatsapp());
            companyResponseDTO.setLastActivity(companies.getLastActivity());
            companyResponseDTO.setLastActivityDate(companies.getLastActivityDate());
            companyResponseDTO.setLinkedPage(companies.getLinkedPage());
            companyResponseDTO.setTimeZone(companies.getTimeZone());
            companyResponseDTO.setWebPage(companies.getWebPage());
            companyResponseDTO.setRfq(companies.getRFQ());
            companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(companies.getWhoFind()));
            companyResponseDTO.setAbout(companies.getAbout());
            companyResponseDTO.setFirstContactDate(companies.getFirstContactDate());
            companyResponseDTO.setIsMailSent(companies.getIsMailSent());
            companyResponseDTO.setIsMsgSent(companies.getIsMsgSent());
            companyResponseDTO.setIsOrder(companies.getIsOrder());
            companyResponseDTO.setNote(companies.getNote());
            //companyResponseDTO.setLead(companyRequestDTO.);
            //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
            // companyResponseDTO.setEmails(emailsService.findEmailsByCompanyName(companyRequestDTO.getName()));
            companyResponseDTO.setCompanyStatus(companies.getCompanyStatus());
            companyResponseDTO.setIndustry(companies.getIndustry());
            companyResponseDTO.setCompanyWhereWasFound(companies.getCompanyWhereWasFound());
            companyResponseDTO.setCompanyType(companies.getCompanyType());

            companyResponseDTOs.add(companyResponseDTO);

    }
        return companyResponseDTOs;

    }

}
























