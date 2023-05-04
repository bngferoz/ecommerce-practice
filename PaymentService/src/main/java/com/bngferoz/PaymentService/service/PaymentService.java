package com.bngferoz.PaymentService.service;

import com.bngferoz.PaymentService.model.PaymentRequest;
import com.bngferoz.PaymentService.model.PaymentRespone;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentRespone getPaymentByOrderId(String orderId);

}
