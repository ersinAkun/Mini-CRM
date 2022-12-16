package com.crm.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.CompanyEmployees;
import com.crm.domain.enums.Department;



@Repository
public interface CompanyEmployeesRepository extends JpaRepository<CompanyEmployees, Long>   {

	
	Boolean existsByEmail(String email);
	
	
	
	// User ve Role arasında ManyToMany ilişkide default olarak LAZY tanımlıydı,
	//			biz bunu EAGER olmasını sağladık @EntityGraph ile
	//@EntityGraph(attributePaths = "roles")
	Optional<CompanyEmployees> findByEmail(String email);
	
	
	@EntityGraph(attributePaths = "id")//rolleri almamak icin 
	   Optional<CompanyEmployees> findEmployeesById(Long id);

	
	@Modifying // JpaRepository içinde custom bir query ile DML operasyonları yapılıyor ise  @Modifying konulur
	   @Query( "UPDATE CompanyEmployees e SET e.firstName=:firstName, e.lastName=:lastName,e.phoneNumber=:phoneNumber,"

	   		+ "e.email=:email,e.address=:address, e.password=:password, e.city=:city, e.country=:country, "

	   		+ "e.email=:email,e.address=:address, e.city=:city, e.country=:country, "

	   		+ "e.employeeDepartment=:employeeDepartment, e.hasWhatsapp=:hasWhatsapp, e.jobTitle=:jobTitle, e.notes=:notes, "
	   		+ "e.speaks=:speaks, e.state=:state  "
	   		+ "WHERE e.id=:id" )
	   void update(@Param("id") Long id,

			   					 @Param("firstName") String firstName,
			   					 @Param("lastName") String lastName,
			   					 @Param("phoneNumber") String phoneNumber,
			   					 @Param("email") String email,
			   					 @Param("address") String address,
			   					@Param("password") String password,
			   					@Param("city") String city,
			   					@Param("country") Department department,
			   					@Param("employeeDepartment") Boolean boolean1,
			   					@Param("hasWhatsapp") String string,
			   					@Param("jobTitle") String jobTitle,
			   					@Param("notes") String notes,
			   					@Param("speaks") String speaks
			   					
			   					);


	
	
}
