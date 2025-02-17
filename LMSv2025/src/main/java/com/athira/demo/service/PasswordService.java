package com.athira.demo.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements IPAsswordService {

	// Hash a password
	public String hashPassword(String password) {
		// Generates a salt and hashes the password
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}

	// Check if a password matches the stored hash
	public boolean checkPassword(String password, String storedHash) {
		// Check if the provided password matches the stored hash
		System.out.println("input "+password);
		System.out.println("hashed "+storedHash);
		System.out.println("pwd: "+BCrypt.checkpw(password, storedHash));
		return BCrypt.checkpw(password, storedHash);
	}
}
