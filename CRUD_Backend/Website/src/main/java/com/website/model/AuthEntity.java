package com.website.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="regDetails")
public class AuthEntity { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(name = "email", nullable = false, unique = true)
    private String email; 
	
	@Column(name = "f_name", nullable = false)
    private String fName;
	
	 @Column(name = "l_name", nullable = false)
	  private String lName;
	 
	 @Column(name = "phone_number", nullable = false)
	  private String phoneNumber; 
	 
	 
	 
	 @Column(name = "password", nullable = false)
	    private String password;
	 
	// Default constructor - required by Hibernate
	    public AuthEntity() {
	    }

	public AuthEntity(String fName, String lName, String phoneNumber, String email, String password,Integer id) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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
	
	@Override
    public String toString() {
        return "AuthEntity{" +
        		", l_name='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ph_num='" + phoneNumber + '\'' +
                ", f_name='" + fName + '\'' +
                ", l_name='" + lName + '\'' +
                '}';
    }
}
