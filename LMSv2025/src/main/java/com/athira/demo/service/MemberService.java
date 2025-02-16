package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.common.Validation;
import com.athira.demo.dao.IMembersDao;
import com.athira.demo.entity.Members;

@Transactional
@Service
public class MemberService implements IMemberService {

	@Autowired
	IMembersDao membersDao;
	
	@Autowired
	private Validation validation;

	public List<Members> getAllMembers() {
		return membersDao.findAll();
	}

	
	public Members getMemberById(Integer id) {
		return membersDao.findById(id).orElseThrow(()-> new RuntimeException("Member not found."));
	}

	public Members saveMember(Members members) {
		
		if (!validation.isValidEmail(members.getEmail())) {
			throw new IllegalArgumentException("Email format is invalid.");
		}
		
		if (!validation.isPhoneNumberValid(members.getPhoneNumber())) {
			throw new IllegalArgumentException("Phone number format is invalid.");
		}
		//when saving a new member
		if (members.getMemberId() == null && members.getJoinDate() == null) {
			 members.setJoinDate(new DateTime()); 
		}
		
	    if (members.getIsActive() == null) {
	        members.setIsActive(true);  // Default to true if null
	    }
		return membersDao.save(members);
	}

	@Transactional
	public Members editMember(Members members) {

		Optional<Members> meOptional = membersDao.findById(members.getMemberId());
		if (!meOptional.isPresent()) {
			throw new Error("Member not found!");
		}

		Members membersEntity = meOptional.get();
		if (members.getJoinDate() != null) {
			membersEntity.setJoinDate(members.getJoinDate());
		}
		membersEntity.setAddress(members.getAddress());
		membersEntity.setEmail(members.getEmail());
		membersEntity.setIsActive(members.getIsActive() != null ? members.getIsActive() : membersEntity.getIsActive());
		membersEntity.setmStatus(members.getmStatus() != null ? members.getmStatus() : membersEntity.getmStatus());
		membersEntity.setName(members.getName());
		membersEntity.setPhoneNumber(members.getPhoneNumber());
		
		return membersDao.save(membersEntity);
	}

	public List<Members> getMemberByName(String name) {
		if(validation.isNameValid(name)) {
			return membersDao.findByName(name);
		}
		return null;
	}


	public List<Members> getMemberByPhone(String phone) {
		if(validation.isPhoneNumberValid(phone)){
			return membersDao.findByPhoneNumber(phone);
		}
		return null;
	}


	public void disableMemberById(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("Enter valid id.");
		}
		membersDao.disableMember(id);
	}

}
