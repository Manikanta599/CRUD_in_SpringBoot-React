package com.website.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.Repo.AuthRepo;
import com.website.Repo.EmployeeRepo;
import com.website.dao.EmployeeDetailsWithReg;
import com.website.model.AuthEntity;
import com.website.model.EmployeeDetails;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private AuthRepo regDetailsRepo;

	@Override
	public List<EmployeeDetailsWithReg> getAllEmployeesWithRegDetails() {
		return empRepo.findAllEmployeesWithRegDetails()
				.stream()
				.map(rec ->{
					EmployeeDetails emp=(EmployeeDetails) rec[0];
					AuthEntity reg=(AuthEntity) rec[1];
					return new EmployeeDetailsWithReg(emp,reg);
				})
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDetails updateEmployeeDetails(int empId, EmployeeDetails newDetails) {
		int updatedRows=empRepo.updateEmployeeDetails(empId,
				newDetails.getDepartment(), 
                newDetails.getPosition(), 
                newDetails.getSalary(), 
                newDetails.getHireDate(), 
                newDetails.getAddress());
		if(updatedRows==0)
		{
			throw new RuntimeException("Employee not found with ID: " + empId);
		}
		return empRepo.findById((long) empId).orElse(null);
	} 
	
	// Delete both EmployeeDetails and RegDetails records
    
	@Transactional
    public boolean deleteEmployeeAndRegDetails(int empId) {
        return empRepo.findById((long) empId).map(employee -> {
            int regId = employee.getReg_id(); // Get regId from the employee details

            // Delete employee by empId and check if successful
            empRepo.deleteById((long) empId);
            boolean employeeDeleted = !empRepo.existsById((long) empId);

            // If employee deleted successfully, delete the regDetails by regId
            if (employeeDeleted) {
                regDetailsRepo.deleteById(regId);
                return !regDetailsRepo.existsById(regId);
            }

            return false;
        }).orElse(false); // Return false if employee not found
    }
}
