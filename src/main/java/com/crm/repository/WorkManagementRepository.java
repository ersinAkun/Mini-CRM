package com.crm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.domain.WorkManagement;

@Repository
public interface WorkManagementRepository  extends JpaRepository<WorkManagement, Long> {

	//@Query( "SELECT count(*) from Property p join p.image img where img.id=:id")
	//Integer findPropertyCountByImageId(@Param("id") String id );
	
	@Query("SELECT w FROM WorkManagement w WHERE assignee.id=:id")
	List<WorkManagement> findTaskWithEmployeeId(@Param("id")Long id);

}
