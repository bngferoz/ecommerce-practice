package com.bngferoz.PaymentService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bngferoz.PaymentService.entity.TransactionDetails;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionDetails, Long> {
	
	TransactionDetails findByOrderId(long orderId);
}
