package com.website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.website.dao.EmployeeDetailsWithReg;
import com.website.model.EmployeeDetails;

@Service
public interface EmployeeService {
	public List<EmployeeDetailsWithReg> getAllEmployeesWithRegDetails();
    public EmployeeDetails updateEmployeeDetails(int empId, EmployeeDetails newDetails);
    public boolean deleteEmployeeAndRegDetails(int empId);
}
