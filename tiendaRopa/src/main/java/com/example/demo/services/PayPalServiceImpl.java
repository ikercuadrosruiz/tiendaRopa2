package com.example.demo.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalServiceImpl implements PayPalService{

	private static final Logger log = LoggerFactory.getLogger(PayPalServiceImpl.class);
	
	@Autowired
	private APIContext context;
	
	public Payment createPayment(
			Double total, 
			String currency, 
			String method, 
			String intent, 
			String description, 
			String cancelUrl, 
			String sucessUrl) throws PayPalRESTException {
		
		Amount amount = new Amount();
		amount.setCurrency(currency);
		
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(Double.toString(total));
		
		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(amount);
		
		List<Transaction> lt = new ArrayList<Transaction>();
		lt.add(transaction);
		
		Payer p = new Payer();
		p.setPaymentMethod(method);
		
		Payment py = new Payment();
		py.setIntent(intent);
		py.setPayer(p);
		py.setTransactions(lt);
		RedirectUrls rurl = new RedirectUrls();
		rurl.setCancelUrl(cancelUrl);
		rurl.setReturnUrl(sucessUrl);
		py.setRedirectUrls(rurl);
		
		return py.create(context);	
	}
	
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		
		Payment py = new Payment();
		py.setId(paymentId);
		
		PaymentExecution pye = new PaymentExecution();
		pye.setPayerId(payerId);
		
		return py.execute(context, pye);
		
	}
	
}
