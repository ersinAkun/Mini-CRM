package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.domain.Emails;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Long> {

    @Query("SELECT e.email FROM Emails e where e.company.id=:id ")
    List<String> findByEmailsNameWithCompanyId(Long id);
}
