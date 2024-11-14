package com.website.Repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.website.model.AuthEntity;

import jakarta.transaction.Transactional;

public interface AuthRepo extends JpaRepository<AuthEntity, Integer> {
    
    AuthEntity findByEmailAndPassword(String email, String password);
    
 // New method to update password by email
    @Modifying
    @Transactional
    @Query("UPDATE AuthEntity a SET a.password = :password WHERE a.email = :email")
    int updatePasswordByEmail(String email, String password); 
    List<AuthEntity> findAll(); 
    
//    @Modifying
//    @Transactional
//    @Query("UPDATE AuthEntity e SET e.email = :email, e.fName = :fName, " +
//            "e.lName = :lName, e.phoneNumber = :phoneNumber, e.password = :password WHERE e.id = :id")
//     int updateRegDetails(int id, String email, String fName, String lName, 
//                               String phoneNumber, String password); 
//   
    
    @Modifying
  @Transactional
    @Query("UPDATE AuthEntity a SET a.email = :email, a.fName = :fName, a.lName = :lName, a.phoneNumber = :phoneNumber, a.password = :password WHERE a.id = :id")
    int updateRegDetails(@Param("id") int id, @Param("email") String email, @Param("fName") String fName, @Param("lName") String lName, @Param("phoneNumber") String phoneNumber, @Param("password") String password);
}

