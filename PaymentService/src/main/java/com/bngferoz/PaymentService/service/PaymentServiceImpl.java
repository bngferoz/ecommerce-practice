package com.bngferoz.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bngferoz.PaymentService.entity.TransactionDetails;
import com.bngferoz.PaymentService.model.PaymentMode;
import com.bngferoz.PaymentService.model.PaymentRequest;
import com.bngferoz.PaymentService.model.PaymentRespone;
import com.bngferoz.PaymentService.repo.TransactionRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	@Override
	public long doPayment(PaymentRequest req) {
		log.info("Recording Payment Details: {}", req);
		
		TransactionDetails transactionDetails = TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(req.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(req.getOrderId())
				.refNo(req.getRefNo())
				.amount(req.getAmount())
				.build();
		transactionRepo.save(transactionDetails);
		log.info("Transaction completed with id: {}",transactionDetails.getId());
		
		return transactionDetails.getId();
	}

	@Override
	public PaymentRespone getPaymentByOrderId(String orderId) {
		log.info("Getting payment info for the order id: {}", orderId);
		TransactionDetails transactionDetails = transactionRepo.findByOrderId(Long.valueOf(orderId));
		PaymentRespone paymentRespone = PaymentRespone.builder()
				.paymentId(transactionDetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate())
				.orderId(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus())
				.amount(transactionDetails.getAmount())
				.build();
		return paymentRespone;
	}

}
