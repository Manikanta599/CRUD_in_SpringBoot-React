package com.website.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeDetails")
public class EmployeeDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

	@Column(name="reg_id",nullable = false)
	private Integer reg_id;
   
    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "address")
    private String address;
    
    

	public EmployeeDetails() {
		
	}



	public EmployeeDetails(Long empId, Integer reg_id, String department, String position, BigDecimal salary,
			LocalDate hireDate, String address) {
		super();
		this.empId = empId;
		this.reg_id = reg_id;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.hireDate = hireDate;
		this.address = address;
	}



	public Long getEmpId() {
		return empId;
	}



	public void setEmpId(Long empId) {
		this.empId = empId;
	}



	public Integer getReg_id() {
		return reg_id;
	}



	public void setReg_id(Integer reg_id) {
		this.reg_id = reg_id;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public BigDecimal getSalary() {
		return salary;
	}



	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}



	public LocalDate getHireDate() {
		return hireDate;
	}



	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



}