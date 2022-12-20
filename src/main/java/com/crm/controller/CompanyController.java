package com.crm.controller;

import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.requestDTO.CompanyRequestDTO;
import com.crm.responseDTO.*;
import com.crm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //**SELİM*********************** CREATE **************************************EMAIL KAYIT ETMİYORUZ ŞUAN!!!
    @PostMapping("/create/{eId}")//employee id'sini aldık
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO, @PathVariable Long eId) {
        companyService.saveCompany(companyRequestDTO, eId);

        CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_CREATED_RESPONSE, true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //**SELİM*********************** UPDATE **************************************
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        companyService.updateCompany(id, companyRequestDTO);

        CrmResponse response = new CrmResponse(ResponseMessage.COMPANY_UPDATE_RESPONSE, true);

        return ResponseEntity.ok(response);

    }

    //**SELİM*********************** DELETE **************************************
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> deleteCompany(@PathVariable Long id) {

        companyService.deleteCompanyById(id);

        CrmResponse crmResponse = new CrmResponse(ResponseMessage.COMPANY_DELETE_RESPONSE_MESSAGE, true);

        return ResponseEntity.ok(crmResponse);
    }

    //**SELİM*********************** GET *****************************************
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long id) {
        CompanyResponseDTO companyResponseDTO = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyResponseDTO);
    }

    //**SELİM*********************** GET ALL *************************************
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        List<CompanyResponseDTO> companyResponseDTOs = companyService.getAllCompanies();

        return ResponseEntity.ok(companyResponseDTOs);
    }


    //**SELİM*********************** GET PAGEABLE ********************************

    @GetMapping("/getPages")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Page<CompanyResponseDTO>> getAllCompaniesByPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction",
                    required = false,
                    defaultValue = "DESC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<CompanyResponseDTO> companyResponseDTOPage = companyService.getCompaniesPage(pageable);

        return ResponseEntity.ok(companyResponseDTOPage);

    }

    //**SELİM*********************** getCompanyWithStatus **********************SELİM**
    @GetMapping("/getWithStatus/{companyStatus}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesByCompanyStatus(@PathVariable CompanyStatus companyStatus) {

        List<CompanyResponseDTO> companyResponseDTOs = companyService.getCompaniesByCompanyStatus(companyStatus);

        return ResponseEntity.ok(companyResponseDTOs);
    }

    //**SELİM*********************** getCompanyWithIndustry ********************SELİM**
    @GetMapping("/getWithIndustry/{industry}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesByIndustry(@PathVariable CompanyIndustry industry) {

        List<CompanyResponseDTO> companyResponseDTOs = companyService.getCompaniesByIndustry(industry);

        return ResponseEntity.ok(companyResponseDTOs);

    }
    //**SELİM***********************getCompanyWithType ************************SELİM**

    @GetMapping("/getWithType/{companyType}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesByCompanyType(@PathVariable CompanyType companyType) {

        List<CompanyResponseDTO> companyResponseDTOs = companyService.getCompaniesByCompanyType(companyType);

        return ResponseEntity.ok(companyResponseDTOs);


    }


}