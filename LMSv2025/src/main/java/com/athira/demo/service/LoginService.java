package com.athira.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.common.APIResponse;
import com.athira.demo.dao.IMembersDao;
import com.athira.demo.dao.IUserDao;
import com.athira.demo.dto.LoginRequestDTO;
import com.athira.demo.dto.SignUpRequestDto;
import com.athira.demo.entity.User;
import com.athira.demo.util.JwtUtils;

@Transactional
@Service
public class LoginService implements ILoginService {

	@Autowired
	IMembersDao memberDao;

	@Autowired
	IUserDao userDao;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IPAsswordService passwordService;

	public APIResponse signUp(SignUpRequestDto signUpRequestDto) {
		APIResponse apiResponse = new APIResponse();

		User user = new User();
		user.setUserName(signUpRequestDto.getUserName());
		user.setEmail(signUpRequestDto.getEmail());

		// Hash the password before saving
		String hashedPassword = passwordService.hashPassword(signUpRequestDto.getPassword());

		user.setPasswordHash(hashedPassword);
		user.setCreatedAt(new DateTime());
		user.setActive(true);

		// Save to Entity ---ORM--Database
		userDao.save(user);

		// generate Token
		String token = jwtUtils.generateJwt(user);

		// Storing more details
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		data.put("email", user.getEmail());

		apiResponse.setData(data);
		apiResponse.setStatus(200);
		return apiResponse;
	}

	@Override
	public APIResponse login(LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		User user = userDao.findByEmailIgnoreCase(loginRequestDTO.getEmail());

		if (user == null) {
			apiResponse.setData("User login failed : User not found!");
			apiResponse.setStatus(500);
			return apiResponse;
		}

		// Verify the password by comparing the entered password with the stored hash
		boolean passwordMatches = passwordService.checkPassword(loginRequestDTO.getPassword(), user.getPasswordHash());

		if (!passwordMatches) {
			apiResponse.setData("User login failed : Incorrect Password");
			apiResponse.setStatus(500);
			return apiResponse;
		}

		// generate Token
		String token = jwtUtils.generateJwt(user);

		// Storing more details
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		data.put("email", user.getEmail());

		apiResponse.setData(data);
		apiResponse.setStatus(200);
		return apiResponse;
	}

}
