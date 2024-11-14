package com.website.service;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.Repo.AuthRepo;
import com.website.model.AuthEntity;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
	
	
	@Autowired
	private AuthRepo authRepo;
	@Override
	//register new user
	public AuthEntity registerUser(AuthEntity authentity)
	{
		System.out.println("in serviceeeee"+authentity);
		return authRepo.saveAndFlush(authentity);
	}
	
	//Authenticate a user for login
	@Override
	public AuthEntity loginUser(String email,String password)
	{
		System.out.println("in login user serviceeee"+email+" "+password);
		return authRepo.findByEmailAndPassword(email, password);
	}
	
	public boolean updatePassword(String email, String newPassword) {
        int rowsAffected = authRepo.updatePasswordByEmail(email, newPassword);
        return rowsAffected > 0;  // Returns true if the password was successfully updated
    }
	
	public List<AuthEntity> getAllUsers() {
        return authRepo.findAll();
    }

	@Override
	@Transactional
	public AuthEntity updaterRegDetails(int id, AuthEntity newDetails) {
		System.out.println("inside the updaterRegDetails" + newDetails);
		int updatedRows=authRepo.updateRegDetails(id,
				newDetails.getEmail(),
				newDetails.getfName(),
				newDetails.getlName(),
				newDetails.getPhoneNumber(),
				newDetails.getPassword()); 
		
		System.out.println(id);
				
		if(updatedRows==0)
		{
			throw new RuntimeException("User not found with ID: " + id);
		}
		System.out.println("from service "+newDetails);
		
		return authRepo.findById(id).orElse(null);
	}

	
	
	
	

}
