package com.bngferoz.orderservice.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bngferoz.orderservice.entity.ProductOrder;

@Repository
public interface OrderRepo extends JpaRepository<ProductOrder, Long>{

}
