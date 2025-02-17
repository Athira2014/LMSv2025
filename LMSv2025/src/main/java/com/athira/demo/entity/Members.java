package com.athira.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "members")
public class Members {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MemberId", nullable = false)
	private Integer memberId;

	@Column(name = "MName", nullable = false, length = 50)
	private String name;

	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;

	@Column(name = "Email", nullable = false, length = 25)
	private String email;

	@Column(name = "Address", nullable = false, length = 100)
	private String address;

	@Column(name = "JoinDate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime joinDate;

	@Column(name = "MStatus", nullable = false, length = 10)
	private String mStatus;

	@Column(name = "IsActive")
	private Boolean isActive;

	@OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;  
	
	@Column(name = "userId", nullable = false)
    private Integer userId; 

	@JsonIgnore
	@OneToMany(mappedBy = "members")
	private List<BorrowTransaction> borrows;

	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Members(Integer memberId, String name, String phoneNumber, String email, String address, DateTime joinDate,
			String mStatus, Boolean isActive, Integer userId, List<BorrowTransaction> borrows) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.joinDate = joinDate;
		this.mStatus = mStatus;
		this.isActive = isActive;
		this.userId = userId;
		this.borrows = borrows;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (name.length() > 50) {
			throw new IllegalArgumentException("Name cannot exceed 50 characters.");
		}
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {

		if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be null or empty.");
		}
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty.");
		}
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null || address.trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be null or empty.");
		}
		if (address.length() > 100) {
	        throw new IllegalArgumentException("Address cannot exceed 20 characters.");
	    }
		this.address = address;
	}

	public DateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(DateTime joinDate) {
		this.joinDate = joinDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<BorrowTransaction> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<BorrowTransaction> borrows) {
		this.borrows = borrows;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		 if (mStatus == null || mStatus.trim().isEmpty()) {
		        throw new IllegalArgumentException("Member status cannot be null or empty.");
		    }
		this.mStatus = mStatus;
	}

	public Members orElse(Object object) {
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
