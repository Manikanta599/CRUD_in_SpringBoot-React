package com.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.website.dao.ApiResponse;
import com.website.dao.EmployeeDetailsWithReg;
import com.website.model.EmployeeDetails;
import com.website.service.EmployeeService;
@RestController
@RequestMapping("empDetails")
public class EmployeeDetailsController {
	
	@Autowired
	private  EmployeeService employeeDetailsService;
	
	@GetMapping("/with-reg")
	
	public ResponseEntity<ApiResponse<List<EmployeeDetailsWithReg>>> getAllEmployeesWithRegDetails() {
	    try {
	        List<EmployeeDetailsWithReg> employees = employeeDetailsService.getAllEmployeesWithRegDetails();

	        if (!employees.isEmpty()) {
	            ApiResponse<List<EmployeeDetailsWithReg>> response = new ApiResponse<>(
	                "Employees retrieved successfully!",
	                0,
	                true,
	                employees
	            );
	            return ResponseEntity.ok(response);
	        } else {
	            ApiResponse<List<EmployeeDetailsWithReg>> response = new ApiResponse<>(
	                "No employees found.",
	                1,
	                false,
	                null
	            );
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        ApiResponse<List<EmployeeDetailsWithReg>> response = new ApiResponse<>(
	            "An error occurred while retrieving employees.",
	            2,
	            false,
	            null
	        );
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	} 
	
	@PutMapping("update/{empId}")
    public ResponseEntity<ApiResponse<EmployeeDetails>> updateEmployeeDetails(
            @PathVariable int empId, @RequestBody EmployeeDetails newDetails) {
        try {
            EmployeeDetails updatedEmployee = employeeDetailsService.updateEmployeeDetails(empId, newDetails);
            
           
            ApiResponse<EmployeeDetails> response = new ApiResponse<>(
                "Employee details updated successfully!", 0, true, updatedEmployee
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<EmployeeDetails> response = new ApiResponse<>(
                "Employee not found.", 1, false, null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
	
	// Delete Employee and Reg Details
    @DeleteMapping("/{empId}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployeeAndRegDetails(@PathVariable int empId) {
        try {
        	boolean res= employeeDetailsService.deleteEmployeeAndRegDetails(empId);
        	ApiResponse<Void> response;
        	if(res)
        	{
        		response = new ApiResponse<>(
                        "Employee and registration details deleted successfully!", 0, true, null
                    );
        	}
        	else
        	{
        		response = new ApiResponse<>(
                        "Employee and registration details Not Found to Delete!", 0, true, null
                    );
        	}
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(
                "An error occurred while deleting employee and registration details.", 1, false, null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	
	
	
}
