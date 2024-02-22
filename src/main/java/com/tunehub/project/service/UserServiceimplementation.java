package com.tunehub.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.project.entity.User;
import com.tunehub.project.repository.UserRepository;

@Service
public class UserServiceimplementation implements UserService {
	@Autowired
	UserRepository urepo;

	
	@Override
	public boolean emailExists(String email) {
		if(urepo.findByEmail(email)==null) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

	@Override
	public String addUser(User user) {
		urepo.save(user);
		return "user is created";
	}


	@Override								  //password from the user
	public boolean validateUser(String email, String password) {
		User user=urepo.findByEmail(email);
		
		//Storing the password from the database
		String db_password=user.getPassword();	
		
		//comparing the password entered by the user with the password present in the database
		if(db_password.equals(password)) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		
		//Extracting the role of email entered by the user and return the value to userController
		return urepo.findByEmail(email).getRole();
	}

	@Override
	public User getUser(String email) {
		return urepo.findByEmail(email);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		urepo.save(user);
		
	}
	
}
