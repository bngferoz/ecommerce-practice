package com.bngferoz.orderservice.service;

import com.bngferoz.orderservice.model.OrderRequest;
import com.bngferoz.orderservice.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

}
