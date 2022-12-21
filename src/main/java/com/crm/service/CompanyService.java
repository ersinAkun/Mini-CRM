package com.crm.service;

import com.crm.domain.Emails;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.exception.ConflictException;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.repository.EmailsRepository;
import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.CompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private EmailsRepository emailsRepository;
    @Autowired
    private EmailsService emailsService;


    //COMPANYEMPLOYEES İÇİN KULLANILIYOR**EMRULLAH
    public Company findCompanyById(Long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
        ;
        return company;
    }


    //**SELİM*********************** CREATE ************************************SELİM**
    public void saveCompany(CompanyRequestDTO companyRequestDTO, Long eId) {

        //girilen isimdeki company sayısına bakıyoruz
        Integer amountCompany = companyRepository.countByCompanyWithName(companyRequestDTO.getName().toUpperCase());

        if (amountCompany > 0) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_ALREADY_CREATED_MESSAGE, companyRequestDTO.getName()));
        }

        Integer employee = companyEmployeesRepository.countById(eId);
        if (!(employee > 0)) {
            throw new ConflictException(String.format(ErrorMessage.EMPLOYEES_NOT_FOUND_BY_ID_MESSAGE, eId));
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
        company.setCompanyStatus(companyRequestDTO.getCompanyStatus());
        company.setIndustry(companyRequestDTO.getIndustry());
        company.setCompanyWhereWasFound(companyRequestDTO.getCompanyWhereWasFound());
        company.setCompanyType(companyRequestDTO.getCompanyType());

        companyRepository.save(company);

        if (companyRequestDTO.getEmails() != null) {
            for (String email : companyRequestDTO.getEmails()) {
                Emails emails = new Emails();
                emails.setCompany(company);
                emails.setEmail(email);
                emailsRepository.save(emails);
            }
        }

    }

//**SELİM*********************** UPDATE ************************************SELİM**

    public void updateCompany(Long id, CompanyRequestDTO companyRequestDTO) {


        findCompanyById(id);

        Company company = new Company();

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


    //**SELİM*********************** GET ***************************************SELİM**
    public CompanyResponseDTO getCompanyById(Long id) {

        Company company = findCompanyById(id);

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setId(company.getId());
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
        // employee nin kayıtlı id si ile adını getirir
        companyResponseDTO.setWhoFind(companyEmployeesService.getNameById(company.getWhoFind()));
        companyResponseDTO.setAbout(company.getAbout());
        companyResponseDTO.setFirstContactDate(company.getFirstContactDate());
        companyResponseDTO.setIsMailSent(company.getIsMailSent());
        companyResponseDTO.setIsMsgSent(company.getIsMsgSent());
        companyResponseDTO.setIsOrder(company.getIsOrder());
        companyResponseDTO.setNote(company.getNote());
        //companyResponseDTO.setLead(companyRequestDTO.);
        //companyResponseDTO.setOrders(ordersService.findOrdersByCompanyId(companyRequestDTO.get));
        companyResponseDTO.setCompanyStatus(company.getCompanyStatus());
        companyResponseDTO.setIndustry(company.getIndustry());
        companyResponseDTO.setCompanyWhereWasFound(company.getCompanyWhereWasFound());
        companyResponseDTO.setCompanyType(company.getCompanyType());
        //list halindeki emailleri responsa tek bir string değerinde kaydeder
        companyResponseDTO.setEmails(emailsService.getEmailsTypeString(id));

        return companyResponseDTO;
    }


    //**SELİM*********************** DELETE ************************************SELİM**
    public void deleteCompanyById(Long id) {
        companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        companyRepository.deleteById(id);
    }


    //**SELİM*********************** GET ALL ***********************************SELİM**
    public List<CompanyResponseDTO> getAllCompanies() {
        List<Company> company = companyRepository.findAll();

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Company companies : company) {

            companyResponseDTOs.add(getCompanyById(companies.getId()));
        }
        return companyResponseDTOs;
    }


    //**SELİM*********************** GET PAGEABLE ******************************SELİM**
    public Page<CompanyResponseDTO> getCompaniesPage(Pageable pageable) {

        Page<Company> companiesPage = companyRepository.findAll(pageable);

        Page<CompanyResponseDTO> responsePage = companiesPage.map(new Function<Company, CompanyResponseDTO>() {

            @Override
            public CompanyResponseDTO apply(Company company) {
                CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

                companyResponseDTO.setId(company.getId());
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

    //**SELİM*********************** getCompanyWithStatus **********************SELİM**
    public List<CompanyResponseDTO> getCompaniesByCompanyStatus(CompanyStatus companyStatus) {
        List<Long> companyId = companyRepository.findCompaniesIdByCompanyStatus(companyStatus);

        if (companyId.size() == 0) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_STATUS_IS_NOT_EXIST_MESSAGE, companyStatus));
        }

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Long id : companyId) {
            // getCompanyById(id) kullanrak kodumuzu kısaltmış olduk
            companyResponseDTOs.add(getCompanyById(id));
        }
        return companyResponseDTOs;
    }

    //**SELİM*********************** getCompanyWithIndustry ********************SELİM**
    public List<CompanyResponseDTO> getCompaniesByIndustry(CompanyIndustry industry) {
        List<Long> companyId = companyRepository.findCompaniesIdByIndustry(industry);

        if (companyId.size() == 0) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_STATUS_IS_NOT_EXIST_MESSAGE, industry));
        }

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Long id : companyId) {

            companyResponseDTOs.add(getCompanyById(id));
        }
        return companyResponseDTOs;


    }
    //**SELİM***********************getCompanyWithType ************************SELİM**

    public List<CompanyResponseDTO> getCompaniesByCompanyType(CompanyType companyType) {
        List<Long> companyId = companyRepository.findCompaniesIdByCompanyType(companyType);

        if (companyId.size() == 0) {
            throw new ConflictException(String.format(ErrorMessage.COMPANY_WITH_STATUS_IS_NOT_EXIST_MESSAGE, companyType));
        }

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();

        for (Long id : companyId) {

            companyResponseDTOs.add(getCompanyById(id));
        }
        return companyResponseDTOs;

    }

}