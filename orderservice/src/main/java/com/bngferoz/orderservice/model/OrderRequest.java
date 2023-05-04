package com.bngferoz.orderservice.model;

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
public class OrderRequest {
	private long productId;
	private long totalAmount;
	private long quantity;
	private PaymentMode paymentMode;
}
