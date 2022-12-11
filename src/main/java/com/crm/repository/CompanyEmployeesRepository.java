package com.crm.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.domain.CompanyEmployees;

@Repository
public interface CompanyEmployeesRepository extends JpaRepository<CompanyEmployees, Long>   {

	
	Boolean existsByEmail(String email);
	
	
	
	// User ve Role arasında ManyToMany ilişkide default olarak LAZY tanımlıydı,
	//			biz bunu EAGER olmasını sağladık @EntityGraph ile
	//@EntityGraph(attributePaths = "roles")
	Optional<CompanyEmployees> findByEmail(String email);
	
	
	

	
	
}
