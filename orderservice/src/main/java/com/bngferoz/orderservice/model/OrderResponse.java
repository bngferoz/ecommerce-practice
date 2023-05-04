package com.bngferoz.orderservice.model;

import java.time.Instant;

import com.bngferoz.orderservice.external.response.PaymentRespone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
	private long orderId;
	private Instant orderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ProductDetails {

		private long productId;
		private String productName;
		private long quantity;
		private long price;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class PaymentDetails {
		private long paymentId;
		private String status;
		private PaymentMode paymentMode;
		private long amount;
		private Instant paymentDate;
		private long orderId;
	}
}
