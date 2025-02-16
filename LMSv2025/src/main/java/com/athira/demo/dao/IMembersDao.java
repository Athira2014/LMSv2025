package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Members;

@Repository
public interface IMembersDao extends JpaRepository<Members, Integer> {

	@Query("from Members WHERE name LIKE %?1%")
	List<Members> findByName(String name);

	@Query("from Members WHERE phoneNumber LIKE %?1%")
	List<Members> findByPhoneNumber(String phone);

	@Modifying
	@Query("UPDATE com.athira.demo.entity.Members SET isActive = 0 WHERE memberId = :id")
	void disableMember(@Param("id") Integer id);

}
