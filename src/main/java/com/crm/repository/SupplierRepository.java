package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.domain.Supplier;


@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Long> {
	

}
