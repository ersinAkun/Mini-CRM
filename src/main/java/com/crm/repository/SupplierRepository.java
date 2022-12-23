package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.OrderedProducts;
import com.crm.domain.Supplier;


@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Long> {

	//*****EMIN***Supplier ürünlerini getirmek için sql sorgusu*******
	
	/*
	 * @Query("SELECT w FROM WorkManagement w WHERE assignee.id=:id")
	List<WorkManagement> findTaskWithEmployeeId(@Param("id")Long id);
	 */
	@Query("SELECT orderedProducts FROM Supplier s WHERE s.id=:id")//sorgu yanlış. inner join filan olması lazım
	List<OrderedProducts> getOrderedProductsWithSupplierID(@Param("id")Long supplierId);
	

}
