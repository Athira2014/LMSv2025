package com.athira.demo.service;

public interface IPAsswordService {
    
	public String hashPassword(String password);
	
	public boolean checkPassword(String password, String storedHash);
}
