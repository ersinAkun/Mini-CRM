package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.OrderedProducts;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, Long>{

	
	
	@Query( "SELECT count(*) from OrderedProducts p join p.image img where img.id=:id")
	Integer findProductCountByImageId(@Param("id") String id );

}
