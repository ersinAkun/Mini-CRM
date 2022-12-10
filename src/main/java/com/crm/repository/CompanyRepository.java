package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.crm.domain.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
//	
//
//	//*****Secilen Id`li Employees`in buldugu Company lerin isimleri gelecek~~~Celebi~~~ (Insallah!!!)
//	@Query("Select c.name from Company c join c.foundedCompanies e where e.id=:id")
//	List<Company> foundedCompaniesByCompanyEmployeesId(Long id);
//
}
