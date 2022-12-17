package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.domain.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long>{

}
