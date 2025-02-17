package com.athira.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.athira.demo.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{

	@Query("from User WHERE LOWER(email) = LOWER(:email)")
	User findByEmailIgnoreCase(@Param("email") String email);
	
}
