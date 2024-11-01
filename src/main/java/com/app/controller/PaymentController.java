package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.PaymentReqDto;
import com.app.services.interfaces.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins={"http://localhost:3000","https://sobikola.netlify.app"})
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<?> getAllPayment(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllPaymentWithUser(@PathVariable Long userId){
		return ResponseEntity.ok(paymentService.getAllPaymentWithUsers(userId));
	}
	
	@PostMapping("/add/{userId}/{orderId}")
	public ResponseEntity<?> makePayment(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody PaymentReqDto paymentDto){
		try {
			return ResponseEntity.ok(paymentService.makePayment(userId, orderId, paymentDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
}
