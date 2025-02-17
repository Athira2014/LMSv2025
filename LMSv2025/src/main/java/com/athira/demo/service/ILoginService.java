package com.athira.demo.service;

import com.athira.demo.common.APIResponse;
import com.athira.demo.dto.LoginRequestDTO;
import com.athira.demo.dto.SignUpRequestDto;

public interface ILoginService {

	APIResponse signUp(SignUpRequestDto signUpRequestDto);

	APIResponse  login(LoginRequestDTO loginRequestDTO);

}
