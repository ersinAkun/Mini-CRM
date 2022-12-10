package com.crm.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.crm.domain.CompanyEmployees;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;
import com.crm.responseDTO.CompanyEmployeesResponseDTO;
@Mapper(componentModel = "spring")
public interface CompanyEmployeesMapper{
	
	
	CompanyEmployeesResponseDTO companyEmployeesToCompanyEmployeesResponseDTO(CompanyEmployees companyEmployees);
	
	
	CompanyEmployees companyEmployeesRequestDTOToCompanyEmployees(CompanyEmployeesRequestDTO companyEmployeesRequestDTO);
	
	List<CompanyEmployeesRequestDTO> companyEmployeesListToCompanyEmployeesRequestDTOList(List<CompanyEmployees> companyEmployeesList);
	
	

}
