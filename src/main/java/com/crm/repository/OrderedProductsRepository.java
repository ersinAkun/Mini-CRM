package com.crm.repository;


import java.util.List;
import java.util.Optional;

import com.crm.domain.Supplier;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crm.domain.OrderedProducts;


@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, Long>{

	
	
	@Query( "SELECT count(*) from OrderedProducts p join p.image img where img.id=:id")
	Integer findProductCountByImageId(@Param("id") String id );
	
	@EntityGraph(attributePaths = "id")
	Optional<OrderedProducts>findOrderedProductsById(Long id);
	
	@Query("SELECT o FROM OrderedProducts o WHERE o.suppliers=:id")
	List<OrderedProducts> findProductsWithSupplierId(@Param("id")Long supplierId);
	

	@Query("SELECT o FROM OrderedProducts o WHERE o.id=:id")
	List<OrderedProducts> findProductsWithOrderId(@Param("id")Long orderId);

	@Query("select o from OrderedProducts o where o.id=:id")
    OrderedProducts getOrderedProduct(@Param("id") Long id);
}
