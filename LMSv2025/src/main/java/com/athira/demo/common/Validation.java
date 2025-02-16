package com.athira.demo.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	// name validation
	public boolean isNameValid(String name) {
		boolean isValid = false;
		if (name != null) {
			try {
				Pattern pattern = Pattern.compile("^[A-Za-z ]+$");
				Matcher nameMatcher = pattern.matcher(name);
				if (!nameMatcher.find()) {
					throw new InvalidNameException("Invalid Name.");
				} else {
					isValid = true;
				}
			} catch (InvalidNameException e) {
				e.getMessage();
			}

		}
		return isValid;
	}

	// Phone number validation
	public boolean isPhoneNumberValid(String phone) {
			String regex = "^\\+?[0-9]*[\\s\\-]?[0-9]+$";
			return phone != null && phone.matches(regex);
	}
	
	public boolean isValidEmail(String email) {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && email.matches(regex);
	}
}
