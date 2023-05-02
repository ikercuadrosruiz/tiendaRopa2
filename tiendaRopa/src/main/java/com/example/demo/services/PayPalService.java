package com.example.demo.services;

import com.example.demo.model.dto.ProductoDTO;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PayPalService {

	Payment createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String sucessUrl) throws PayPalRESTException ;
	
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
