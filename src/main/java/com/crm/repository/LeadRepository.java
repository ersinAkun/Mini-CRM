package com.crm.repository;

import com.crm.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.domain.Lead;
import org.springframework.data.jpa.repository.Query;

public interface LeadRepository extends JpaRepository<Lead, Long>{


}
