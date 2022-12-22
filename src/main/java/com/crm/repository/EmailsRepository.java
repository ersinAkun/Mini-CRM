package com.crm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.crm.domain.Emails;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Long> {

    @Query("SELECT e.email FROM Emails e where e.company.id=:id ")
    List<String> findByEmailsNameWithCompanyId(Long id);
}
