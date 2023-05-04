package com.bngferoz.PaymentService.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long orderId;
	private String paymentMode;
	private String refNo;
	private Instant paymentDate;
	private String paymentStatus;
	private long amount;
}	
