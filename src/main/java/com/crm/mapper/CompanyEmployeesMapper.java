package com.crm.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.crm.domain.CompanyEmployees;
import com.crm.requestDTO.CompanyEmployeesRequestDTO;
@Mapper(componentModel = "spring")
public interface CompanyEmployeesMapper{
	
	
	CompanyEmployeesRequestDTO companyEmployeesToCompanyEmployeesRequestDTO(CompanyEmployees companyEmployees);
	
	
	CompanyEmployees companyEmployeesRequestDTOToCompanyEmployees(CompanyEmployeesRequestDTO companyEmployeesRequestDTO);
	
	List<CompanyEmployeesRequestDTO> companyEmployeesListToCompanyEmployeesRequestDTOList(List<CompanyEmployees> companyEmployeesList);
	
	

}
