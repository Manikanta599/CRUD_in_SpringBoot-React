package com.website.Repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.website.model.EmployeeDetails;

import jakarta.transaction.Transactional;

public interface EmployeeRepo extends JpaRepository<EmployeeDetails,Long>{
	@Query("SELECT e, r FROM EmployeeDetails e JOIN AuthEntity r ON e.reg_id = r.id")
    List<Object[]> findAllEmployeesWithRegDetails();
    
    @Modifying
    @Transactional
    @Query("UPDATE EmployeeDetails e SET e.department = :department, e.position = :position, " +
           "e.salary = :salary, e.hireDate = :hireDate, e.address = :address WHERE e.empId = :empId")
    int updateEmployeeDetails(int empId, String department, String position, BigDecimal salary, 
                              LocalDate hireDate, String address);
}
