package com.tunehub.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.project.entity.Songs;
import com.tunehub.project.entity.User;
import com.tunehub.project.service.SongsService;
import com.tunehub.project.service.UserService;

import jakarta.servlet.http.HttpSession;

//This Controller class is used to connect with the SERVICES

@Controller
public class UserController 
{
	@Autowired
	UserService userv;
	
	@Autowired
	SongsService songserv;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		//Checking if the email is present in the database
		//if the email entered by the user is not present in the database, then return registration successful
		if(userv.emailExists(user.getEmail())== false) 
		{
			userv.addUser(user);
			return "registersuccess";
		}
		//else email entered by the user is present in the database, return registration fail
		else 
		{
			return "registerfail";
		}
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password, HttpSession session ) 
	{
		//storing the returned value by the service 
		//if the password & email entered by the user matches with the email & password stored in the database, then login successful
		if(userv.validateUser(email, password)==true) 
		{
			//setting the session is=f the user is validated
			session.setAttribute("email", email);
			
			//if the role of the email entered by the user is equals to "admin", then return admin home page
			if(userv.getRole(email).equals("admin"))
			{
			return "adminhome";
			}
			
			//if the role of the email entered by the user is equals to "customer", then return customer home page
			else 
			{
			return "customerhome";
			}
		}
		//if the password & email entered by the user does not matches with the email & password stored in the database, then again login 
		else 
		{
			return "loginfail";
		}
	}
	
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session, Model model) {
		
		String email=(String) session.getAttribute("email");
		//getUser() method takes the entire data by email of a user
		User user=userv.getUser(email);
		
		//checking the status of user either he has premium or not
		boolean userStatus=user.isPremium();
		if(userStatus==true) {
			List<Songs> songslist=songserv.fetchAllSongs();
			model.addAttribute("songslist",     songslist);
			return "displaysongs";
		}
		else {
			return "payment";
		}
	}
}
