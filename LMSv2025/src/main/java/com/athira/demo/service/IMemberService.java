package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Members;

public interface IMemberService {

	List<Members> getAllMembers();

	Members getMemberById(Integer id);

	Members saveMember(Members members);

	Members editMember(Members members);

	List<Members> getMemberByName(String name);

	List<Members> getMemberByPhone(String phone);

	void disableMemberById(Integer id);

}
