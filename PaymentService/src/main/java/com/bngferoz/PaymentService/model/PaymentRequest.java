package com.bngferoz.PaymentService.model;

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
@Builder
@ToString
public class PaymentRequest {
	private long orderId;
	private long amount;
	private String refNo;
	private PaymentMode paymentMode;
	
}
