package com.crm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crm.domain.CompanyEmployees;
import com.crm.domain.Role;
import com.crm.domain.enums.RoleType;
import com.crm.exception.BadRequestException;
import com.crm.exception.ConflictException;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.CompanyEmployeesRepository;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdateAdminRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdatePasswordRequestDTO;
import com.crm.requestDTO.CompanyEmployeesUpdateRequestDTO;
import com.crm.responseDTO.CompanyEmployeesResponseDTO;
import com.crm.security.SecurityUtils;

@Service
public class CompanyEmployeesService {

    private PasswordEncoder passwordEncoder;

    private CompanyEmployeesRepository companyEmployeesRepository;

    // private CompanyRepository companyRepository;

    private RoleService roleService;

    @Autowired
    public CompanyEmployeesService(@Lazy PasswordEncoder passwordEncoder,
                                   CompanyEmployeesRepository companyEmployeesRepository, RoleService roleService) {
        super();
        this.passwordEncoder = passwordEncoder;
        this.companyEmployeesRepository = companyEmployeesRepository;
        this.roleService = roleService;


    }


    //********************LoginEmployees********************
    public CompanyEmployees getCurrentEmployee() {

        String email = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRINCIPAL_FOUND_MESSAGE));
        CompanyEmployees companyEmployees = getEmployeeByEmail(email);
        return companyEmployees;

    }


    //*****CELEBI********CREATE EMPLOYEES**********************


    // *****CELEBI********CREATE EMPLOYEES**********************


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
        companyEmployees.setRoles(roles);
        companyEmployeesRepository.save(companyEmployees);

    }

    // *****CELEBI********GET BY EMAIL EMPLOYEES**********************
    // security için
    public CompanyEmployees getEmployeeByEmail(String email) {

        CompanyEmployees companyEmployees = companyEmployeesRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.EMPLOYEES_NOT_FOUND_MESSAGE, email))

        );

        return companyEmployees;
    }

    // *****CELEBI********GET BY ID EMPLOYEES**********************
    public CompanyEmployeesResponseDTO getEmployeesById(Long id) {
        CompanyEmployees companyEmployees = companyEmployeesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        Role role = roleService.findByType(RoleType.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // List<Company> companyNames=
        // companyRepository.foundedCompaniesByCompanyEmployeesId(id);

        CompanyEmployeesResponseDTO companyEmployeesResponseDTO = new CompanyEmployeesResponseDTO();

        companyEmployeesResponseDTO.setFirstName(companyEmployees.getFirstName());
        companyEmployeesResponseDTO.setId(companyEmployees.getId());
        companyEmployeesResponseDTO.setLastName(companyEmployees.getLastName());
        companyEmployeesResponseDTO.setEmail(companyEmployees.getEmail());
        companyEmployeesResponseDTO.setJobTitle(companyEmployees.getJobTitle());
        companyEmployeesResponseDTO.setPhoneNumber(companyEmployees.getPhoneNumber());
        companyEmployeesResponseDTO.setAddress(companyEmployees.getAddress());
        companyEmployeesResponseDTO.setCity(companyEmployees.getCity());
        companyEmployeesResponseDTO.setCountry(companyEmployees.getCountry());
        companyEmployeesResponseDTO.setState(companyEmployees.getState());
        companyEmployeesResponseDTO.setHasWhatsapp(companyEmployees.getHasWhatsapp());
        companyEmployeesResponseDTO.setNotes(companyEmployees.getNotes());
        companyEmployeesResponseDTO.setSpeaks(companyEmployees.getSpeaks());
        companyEmployeesResponseDTO.setBuiltIn(companyEmployees.getBuiltIn());
        companyEmployeesResponseDTO.setEmployeeDepartment(companyEmployees.getEmployeeDepartment());
        // companyEmployeesResponseDTO.setFoundedCompanies(companyNames);//sadece isimleri gelebilir.
        companyEmployeesResponseDTO.setRoles(roles);

        return companyEmployeesResponseDTO;
    }

    // ******CELEBI*******GET ALL EMPLOYEES**********************
    public List<CompanyEmployeesResponseDTO> getAllEmployees() {
        List<CompanyEmployees> companyEmployees = companyEmployeesRepository.findAll();

        // CompanyEmployeesResponseDTO companyEmployeesResponseDTO = new
        // CompanyEmployeesResponseDTO();
        List<CompanyEmployeesResponseDTO> companyEmployeesResponseDTOs = new ArrayList<>();

        for (CompanyEmployees employees : companyEmployees) {

            CompanyEmployeesResponseDTO companyEmployeesResponseDTO = new CompanyEmployeesResponseDTO();
            companyEmployeesResponseDTO.setId(employees.getId());
            companyEmployeesResponseDTO.setFirstName(employees.getFirstName());
            companyEmployeesResponseDTO.setLastName(employees.getLastName());
            companyEmployeesResponseDTO.setEmail(employees.getEmail());
            companyEmployeesResponseDTO.setJobTitle(employees.getJobTitle());
            companyEmployeesResponseDTO.setPhoneNumber(employees.getPhoneNumber());
            companyEmployeesResponseDTO.setAddress(employees.getAddress());
            companyEmployeesResponseDTO.setCity(employees.getCity());
            companyEmployeesResponseDTO.setCountry(employees.getCountry());
            companyEmployeesResponseDTO.setState(employees.getState());
            companyEmployeesResponseDTO.setHasWhatsapp(employees.getHasWhatsapp());
            companyEmployeesResponseDTO.setNotes(employees.getNotes());
            companyEmployeesResponseDTO.setSpeaks(employees.getSpeaks());
            companyEmployeesResponseDTO.setBuiltIn(employees.getBuiltIn());
            companyEmployeesResponseDTO.setEmployeeDepartment(employees.getEmployeeDepartment());
            // companyEmployeesResponseDTO.setFoundedCompanies(companyNames);
            companyEmployeesResponseDTO.setRoles(employees.getRoles());

            companyEmployeesResponseDTOs.add(companyEmployeesResponseDTO);// list e ekliyoruz
        }

        return companyEmployeesResponseDTOs;
    }


    //****CELEBI*********GET PAGE EMPLOYEES**********************


    // ****CELEBI*********GET PAGE EMPLOYEES**********************


    public Page<CompanyEmployeesResponseDTO> getEmployeesPage(Pageable pageable) {
        Page<CompanyEmployees> employeesPage = companyEmployeesRepository.findAll(pageable);

        Page<CompanyEmployeesResponseDTO> responsePage = employeesPage
                .map(new Function<CompanyEmployees, CompanyEmployeesResponseDTO>() {

                    @Override
                    public CompanyEmployeesResponseDTO apply(CompanyEmployees companyEmployees) {
                        CompanyEmployeesResponseDTO companyEmployeesResponseDTO = new CompanyEmployeesResponseDTO();

                        companyEmployeesResponseDTO.setId(companyEmployees.getId());
                        companyEmployeesResponseDTO.setFirstName(companyEmployees.getFirstName());
                        companyEmployeesResponseDTO.setLastName(companyEmployees.getLastName());
                        companyEmployeesResponseDTO.setEmail(companyEmployees.getEmail());
                        companyEmployeesResponseDTO.setJobTitle(companyEmployees.getJobTitle());
                        companyEmployeesResponseDTO.setPhoneNumber(companyEmployees.getPhoneNumber());
                        companyEmployeesResponseDTO.setAddress(companyEmployees.getAddress());
                        companyEmployeesResponseDTO.setCity(companyEmployees.getCity());
                        companyEmployeesResponseDTO.setCountry(companyEmployees.getCountry());
                        companyEmployeesResponseDTO.setState(companyEmployees.getState());
                        companyEmployeesResponseDTO.setHasWhatsapp(companyEmployees.getHasWhatsapp());
                        companyEmployeesResponseDTO.setNotes(companyEmployees.getNotes());
                        companyEmployeesResponseDTO.setSpeaks(companyEmployees.getSpeaks());
                        companyEmployeesResponseDTO.setBuiltIn(companyEmployees.getBuiltIn());
                        companyEmployeesResponseDTO.setEmployeeDepartment(companyEmployees.getEmployeeDepartment());
                        // companyEmployeesResponseDTO.setFoundedCompanies(companyNames);
                        companyEmployeesResponseDTO.setRoles(companyEmployees.getRoles());

                        return companyEmployeesResponseDTO;
                    }

                });

        return responsePage;
    }

    // GET BY ID POJO
    public CompanyEmployees getCompanyEmployees(Long id) {

        CompanyEmployees companyEmployees = companyEmployeesRepository.findEmployeesById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.EMPLOYEES_NOT_FOUND_MESSAGE, id)));
        return companyEmployees;
    }


    // ********Request ten gelen Role bilgisini bizim istedigimiz ROLE_USER gibi
    // sekle ceviriyor*****
    public Set<Role> convertRoles(Set<String> pRoles) {
        Set<Role> roles = new HashSet<>();

        if (pRoles == null) {
            Role userRole = roleService.findByType(RoleType.ROLE_USER);
            roles.add(userRole);
        } else {
            pRoles.forEach(roleStr -> {
                if (roleStr.equals(RoleType.ROLE_ADMIN.getName())) { // Administrator
                    Role adminRole = roleService.findByType(RoleType.ROLE_ADMIN);
                    roles.add(adminRole);

                } else {
                    Role userRole = roleService.findByType(RoleType.ROLE_USER);
                    roles.add(userRole);
                }
            });

        }

        return roles;
    }

    // ********CELEBI*****UPDATE LOGIN EMPLOYEES**********************


    @Transactional
    // // VeritabanÄ± Ã¼zerinde gerÃ§ekleÅŸtirilen bir grup SQL iÅŸleminin tek bir
    // bÃ¼tÃ¼n
    // olarak ele alÄ±nmasÄ±nÄ± saÄŸlar, bir veya daha fazla SQL iÅŸluseremi tek bir
    // iÅŸlem gibi ele alÄ±nÄ±r.
    public void updateLoginEmployees(@Valid CompanyEmployeesUpdateRequestDTO companyEmployeesUpdateRequestDTO) {
        CompanyEmployees companyEmployees = getCurrentEmployee();

        // builtIn kontrolu
        if (companyEmployees.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        boolean emailExist = companyEmployeesRepository.existsByEmail(companyEmployeesUpdateRequestDTO.getEmail());

        if (emailExist && !companyEmployeesUpdateRequestDTO.getEmail().equals(companyEmployees.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE,
                    companyEmployeesUpdateRequestDTO.getEmail()));
        }
        // password boÅŸ ise
//		      if(companyEmployeesRequestDTO.getPassword()==null) {
//		    	  companyEmployeesRequestDTO.setPassword(companyEmployees.getPassword());
//		      } else  {
//		    	  String encodedPassword =  passwordEncoder.encode(companyEmployeesRequestDTO.getPassword());
//		    	  companyEmployeesRequestDTO.setPassword(encodedPassword);
//		      }

        companyEmployeesRepository.update(companyEmployees.getId(), companyEmployeesUpdateRequestDTO.getFirstName(),
                companyEmployeesUpdateRequestDTO.getLastName(), companyEmployeesUpdateRequestDTO.getPhoneNumber(),
                companyEmployeesUpdateRequestDTO.getEmail(), companyEmployeesUpdateRequestDTO.getAddress(),
                companyEmployeesUpdateRequestDTO.getCity(), companyEmployeesUpdateRequestDTO.getCountry(),
                companyEmployeesUpdateRequestDTO.getEmployeeDepartment(),
                companyEmployeesUpdateRequestDTO.getHasWhatsapp(), companyEmployeesUpdateRequestDTO.getJobTitle(),
                companyEmployeesUpdateRequestDTO.getNotes(), companyEmployeesUpdateRequestDTO.getSpeaks(),
                companyEmployeesUpdateRequestDTO.getState());

    }

    // ****CELEBI*********ADMIN UPDATE BY ID EMPLOYEES**********************
    public void updateEmployees(Long id, CompanyEmployeesUpdateAdminRequestDTO companyEmployeesUpdateAdminRequestDTO) {

        CompanyEmployees companyEmployees = getCompanyEmployees(id);

        boolean emailExist = companyEmployeesRepository.existsByEmail(companyEmployeesUpdateAdminRequestDTO.getEmail());

        if (emailExist && !companyEmployeesUpdateAdminRequestDTO.getEmail().equals(companyEmployees.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE,
                    companyEmployeesUpdateAdminRequestDTO.getEmail()));
        }

        // TODO bakilacak
        Set<String> employeesStrRoles = companyEmployeesUpdateAdminRequestDTO.getRoles();

        Set<Role> roles = convertRoles(employeesStrRoles);

        companyEmployees.setBuiltIn(companyEmployeesUpdateAdminRequestDTO.getBuiltIn());
        companyEmployees.setAddress(companyEmployeesUpdateAdminRequestDTO.getAddress());
        companyEmployees.setCity(companyEmployeesUpdateAdminRequestDTO.getCity());
        companyEmployees.setCountry(companyEmployeesUpdateAdminRequestDTO.getCountry());
        companyEmployees.setEmail(companyEmployeesUpdateAdminRequestDTO.getEmail());
        companyEmployees.setEmployeeDepartment(companyEmployeesUpdateAdminRequestDTO.getEmployeeDepartment());
        companyEmployees.setFirstName(companyEmployeesUpdateAdminRequestDTO.getFirstName());
        companyEmployees.setHasWhatsapp(companyEmployeesUpdateAdminRequestDTO.getHasWhatsapp());
        companyEmployees.setJobTitle(companyEmployeesUpdateAdminRequestDTO.getJobTitle());
        companyEmployees.setLastName(companyEmployeesUpdateAdminRequestDTO.getLastName());
        companyEmployees.setNotes(companyEmployeesUpdateAdminRequestDTO.getNotes());
        companyEmployees.setPhoneNumber(companyEmployeesUpdateAdminRequestDTO.getPhoneNumber());
        companyEmployees.setSpeaks(companyEmployeesUpdateAdminRequestDTO.getSpeaks());
        companyEmployees.setState(companyEmployeesUpdateAdminRequestDTO.getState());
        companyEmployees.setRoles(roles);
        companyEmployeesRepository.save(companyEmployees);
    }

    // ********CELEBI*****UPDATE CURRENT USER'S PASSWORD **********************
    public void updatePassword(
            @Valid CompanyEmployeesUpdatePasswordRequestDTO companyEmployeesUpdatePasswordRequestDTO) {
        CompanyEmployees companyEmployees = getCurrentEmployee();

        // builtIn kontrolu
        if (companyEmployees.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        // Formda girilen OldPassword bilgisi ile DB deki password aynı mı kontrol
        // ediyoruz
        if (!passwordEncoder.matches(companyEmployeesUpdatePasswordRequestDTO.getOldPassword(),
                companyEmployees.getPassword())) {
            throw new BadRequestException(ErrorMessage.PASSWORD_NOT_MATCHED);
        }

        // yeni gelen password encode ediliyor
        String hashedPassword = passwordEncoder.encode(companyEmployeesUpdatePasswordRequestDTO.getNewPassword());
        companyEmployees.setPassword(hashedPassword);
        companyEmployeesRepository.save(companyEmployees);
    }


    //****CELEBI*********DELETE BY ID EMPLOYEES**********************

    // ****CELEBI*********DELETE BY ID EMPLOYEES**********************

    public void removeEmployeesById(Long id) {
        CompanyEmployees companyEmployees = getCompanyEmployees(id);

        // builtIn mi kontrol ediyoruz
        if (companyEmployees.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);

        }
        companyEmployeesRepository.deleteById(id);

    }

    // **selim** Employee ad ve soyadını birleştirip company response işlemleri için gönderir **selim**
    public String getNameById(Long whoFind) {
        String firstName = getCompanyEmployees(whoFind).getFirstName();

        String lastName = getCompanyEmployees(whoFind).getLastName();

        String name = firstName + " " + lastName;

        return name;
    }


}
