package com.website.dao;

import com.website.model.AuthEntity;
import com.website.model.EmployeeDetails;

public class EmployeeDetailsWithReg {
	private EmployeeDetails empDetails;
	private AuthEntity regDetails;
	public EmployeeDetailsWithReg() {
		super();
	}
	public EmployeeDetailsWithReg(EmployeeDetails empDetails, AuthEntity regDetails) {
		super();
		this.empDetails = empDetails;
		this.regDetails = regDetails;
	}
	public EmployeeDetails getEmpDetails() {
		return empDetails;
	}
	public void setEmpDetails(EmployeeDetails empDetails) {
		this.empDetails = empDetails;
	}
	public AuthEntity getRegDetails() {
		return regDetails;
	}
	public void setRegDetails(AuthEntity regDetails) {
		this.regDetails = regDetails;
	}
}
