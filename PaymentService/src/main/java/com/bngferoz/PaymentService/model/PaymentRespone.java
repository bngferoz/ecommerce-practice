package com.bngferoz.PaymentService.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PaymentRespone {
	private long paymentId;
	private String status;
	private PaymentMode paymentMode;
	private long amount;
	private Instant paymentDate;
	private long orderId;
}
