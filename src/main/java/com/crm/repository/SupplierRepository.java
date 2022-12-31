package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.Supplier;


@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Long> {

@Query("select s from Supplier s where s.id=:id")
    Supplier getSuplierById(@Param("id") Long id);
}
