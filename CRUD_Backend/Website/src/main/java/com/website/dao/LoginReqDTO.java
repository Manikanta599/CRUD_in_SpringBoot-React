package com.website.dao;

public class LoginReqDTO {
	
	private String email;
    private String password;
    
    

//    public LoginReqDTO(String email, String password) {
//		super();
//		this.email = email;
//		this.password = password;
//	}

	// Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
