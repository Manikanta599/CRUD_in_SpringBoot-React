package com.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.website.dao.ApiResponse;
import com.website.dao.LoginReqDTO;
import com.website.model.AuthEntity;
import com.website.model.EmployeeDetails;
import com.website.service.AuthService;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthEntity>> registerUser(@RequestBody AuthEntity authEntity) {
        try {
        	System.out.println("coming controller");
            authService.registerUser(authEntity); // Register user 
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                    "User registered successfully!", 
                    0, 
                    true, 
                    authEntity // Passing AuthEntity as the data
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Returning response with `null` for data but keeping the type consistent (AuthEntity)
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                    "User registration failed.", 
                    1, 
                    false, 
                    null // No data in case of failure
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthEntity>> loginUser(@RequestBody LoginReqDTO loginRequest) {
    	// Get email and password from the request body
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Authenticate user
        AuthEntity user = authService.loginUser(email, password);
        if (user != null) {
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                    "Login successful!",
                    0,
                    true,
                    user
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                    "Invalid credentials.",
                    1,
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    @PutMapping("/update-password")
    public ResponseEntity<ApiResponse<LoginReqDTO>> updatePassword(@RequestBody LoginReqDTO loginRequest) {
    	// Get email and password from the request body
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Authenticate user
        boolean res = authService.updatePassword(email, password);
        if (res) {
            ApiResponse<LoginReqDTO> response = new ApiResponse<>(
                    "Login successful!",
                    0,
                    true,
                    loginRequest
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<LoginReqDTO> response = new ApiResponse<>(
                    "Invalid credentials.",
                    1,
                    false,
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<AuthEntity>>> getAllUsers() {
        try {
            List<AuthEntity> users = authService.getAllUsers();
            
            if (!users.isEmpty()) {
                ApiResponse<List<AuthEntity>> response = new ApiResponse<>(
                    "Users retrieved successfully!",
                    0,
                    true,
                    users
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<List<AuthEntity>> response = new ApiResponse<>(
                    "No users found.",
                    1,
                    false,
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<List<AuthEntity>> response = new ApiResponse<>(
                "An error occurred while retrieving users.",
                2,
                false,
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    } 
    
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<AuthEntity>> updateRegDetails(
            @PathVariable int id, @RequestBody AuthEntity newDetails) {
        try {
        	AuthEntity updatedEmployee = authService.updaterRegDetails(id, newDetails);
            
   
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                "Employee details updated successfully!", 0, true, updatedEmployee
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<AuthEntity> response = new ApiResponse<>(
                "Employee not found.", 1, false, null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
