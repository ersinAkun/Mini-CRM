package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.domain.Emails;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Long> {

}
