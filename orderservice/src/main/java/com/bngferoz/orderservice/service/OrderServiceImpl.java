package com.bngferoz.orderservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bngferoz.orderservice.entity.ProductOrder;
import com.bngferoz.orderservice.exception.OrderServiceCustomException;
import com.bngferoz.orderservice.external.client.PaymentService;
import com.bngferoz.orderservice.external.client.ProductService;
import com.bngferoz.orderservice.external.request.PaymentRequest;
import com.bngferoz.orderservice.external.response.PaymentRespone;
import com.bngferoz.orderservice.external.response.ProductResponse;
import com.bngferoz.orderservice.model.OrderRequest;
import com.bngferoz.orderservice.model.OrderResponse;
import com.bngferoz.orderservice.repo.OrderRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public long placeOrder(OrderRequest req) {
		
		log.info("Checking if ordered quantity is available and reduce quantity.....");

		//deduct data from product db
		productService.reduceQuantity(req.getProductId(), req.getQuantity());
		
		//save data in order db
		log.info("Placing Order Request: {}",req);
		
		ProductOrder order = new ProductOrder();
		order.setAmount(req.getTotalAmount());
		order.setOrderDate(Instant.now());
		order.setOrderStatus("CREATED");
		order.setQuantity(req.getQuantity());
		order.setProductId(req.getProductId());
		
		orderRepo.save(order);
		log.info("Order placed successfully with order id: {}", order.getId());
		
		
		
		//complete payment
		log.info("Calling Payment Service to complete the payment");
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getId())
				.paymentMode(req.getPaymentMode())
				.amount(req.getTotalAmount())
				.build();
		
		String orderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment is done successfully!");
			orderStatus = "PLACED";
		}catch (Exception e) {
			log.error("Error occurred during payment");
			orderStatus = "PAYMENT_FAILED";
		}
		order.setOrderStatus(orderStatus);
		orderRepo.save(order);
		
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get order details by order id: {}", orderId);
		ProductOrder order = orderRepo.findById(orderId)
				.orElseThrow(()->new OrderServiceCustomException("Order not found for the order id: "+orderId, "NOT_FOUND", 404));
		
		log.info("Getting product details from product service. Product id: {}",order.getProductId());
		
		ProductResponse res = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);
		OrderResponse.ProductDetails productDetails = 
				OrderResponse.ProductDetails.builder()
				.productName(res.getProductName())
				.productId(res.getProductId())
				.build();
		
		log.info("Getting payment information from payment service by order id: {}", orderId);
		PaymentRespone pres = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+orderId,PaymentRespone.class);
		OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
				.paymentId(pres.getPaymentId())
				.status(pres.getStatus())
				.paymentDate(pres.getPaymentDate())
				.paymentMode(pres.getPaymentMode())
				.build();
		
		
		OrderResponse orderResponse = OrderResponse.builder()
				.orderId(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.productDetails(productDetails)
				.paymentDetails(paymentDetails)
				.build();
		return orderResponse;
	}

}
