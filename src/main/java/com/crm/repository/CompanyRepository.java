package com.crm.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.Company;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    
	//*****Secilen Id`li Employees`in buldugu Company lerin isimleri gelecek~~~Celebi~~~
	@Query("SELECT c.name FROM Company c WHERE c.whoFind=:id")
	List<String> foundedCompaniesByCompanyEmployeesId(@Param("id") Long id);


    @Query("SELECT count (c) FROM Company c where c.name=:name")
    Integer countByCompanyWithName(@Param("name") String name);

//


    // @Query("SELECT c FROM Company c where c.companyStatus=:companyStatus ")
    // List<Company> findCompaniesByCompanyStatus(@Param("companyStatus")CompanyStatus companyStatus);

    // @Query("SELECT c FROM Company c where c.industry=:industry ")

    // List<Company> findCompaniesByIndustry(@Param("industry")CompanyIndustry industry);

    // @Query("SELECT c FROM Company c where c.companyType=:companyType ")

    // List<Company> findCompaniesByCompanyType(@Param("companyType")CompanyType companyType);
    @Query("SELECT c.id FROM Company c where c.companyStatus=:companyStatus ")
    List<Long> findCompaniesIdByCompanyStatus(CompanyStatus companyStatus);

    @Query("SELECT c.id FROM Company c where c.industry=:industry ")
    List<Long> findCompaniesIdByIndustry(@Param("industry") CompanyIndustry industry);

    @Query("SELECT c.id FROM Company c where c.companyType=:companyType ")
    List<Long> findCompaniesIdByCompanyType(@Param("companyType") CompanyType companyType);
@Query("select c.name from Company c where c.id=:id")
    String getCompanyNameById(@Param("id") Long companyIdByLeadId);
}