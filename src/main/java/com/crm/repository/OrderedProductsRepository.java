package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.domain.OrderedProducts;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, Long>{

}
