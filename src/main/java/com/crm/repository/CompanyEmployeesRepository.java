package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.domain.CompanyEmployees;

@Repository
public interface CompanyEmployeesRepository extends JpaRepository<CompanyEmployees, Long>   {

}
