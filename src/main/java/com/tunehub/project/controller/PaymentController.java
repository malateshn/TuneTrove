package com.tunehub.project.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.tunehub.project.entity.User;
import com.tunehub.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	
	@Autowired
	UserService userv;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order =null;
		try
		{
			RazorpayClient razorpay = new RazorpayClient("rzp_test_k2hRA7hRrXG8Zu", "BOEB737YTI02uUTGijRJrAv0");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

			order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return order.toString();
	}
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_k2hRA7hRrXG8Zu", "BOEB737YTI02uUTGijRJrAv0");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "BOEB737YTI02uUTGijRJrAv0");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) {
		
		//it returns the object type of value, hence casting is done to String type
		String email=(String) session.getAttribute("email");
		//getting the user details by email
		User user=userv.getUser(email);
		
		//setting the user premium status as true after payment successful
		user.setPremium(true);
		
		//after setting true, updating the user object
		userv.updateUser(user);
		
		//redirecting to login page
		return "login";
	}
	
	@GetMapping("payment-failure")
	public String paymentSuccess() {
		//redirecting to payment error
		return "login";
	}
}
