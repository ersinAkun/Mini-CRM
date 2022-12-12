package com.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.domain.WorkManagement;

@Repository
public interface WorkManagementRepository  extends JpaRepository<WorkManagement, Long> {

}
