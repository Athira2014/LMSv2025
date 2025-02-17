package com.athira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.dto.LoginRequestDTO;
import com.athira.demo.dto.SignUpRequestDto;
import com.athira.demo.service.ILoginService;
import com.athira.demo.util.JwtUtils;

/*
 * It allows your backend to accept requests from different origins (domains, protocols, or ports),
 *  which is useful for situations where your frontend and backend are hosted on different servers or ports.*/
@CrossOrigin
@RestController
@RequestMapping("api/")
public class LoginController {

	@Autowired
	ILoginService loginService;

	@Autowired
	JwtUtils jwtUtils;

	// SignUp
	@PostMapping("signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

		APIResponse apiResponse = loginService.signUp(signUpRequestDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// Login
	@PostMapping("login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

}
