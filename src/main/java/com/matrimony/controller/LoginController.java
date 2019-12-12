package com.matrimony.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.LoginDto;
import com.matrimony.dto.ResponseDto;
import com.matrimony.entity.User;
import com.matrimony.service.LoginService;


/**
 * @description - LoginController - We can check the user login functionalities
 *              with given username and password.
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	/**
	 * login checks with userId and password
	 * @param userDto -> params are userId and password.
	 * @return response is success or failure
	 */
	
	@PostMapping(value = "/login")
	public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
		logger.info("user login checks...");
		ResponseDto  responseDto;
		User user = loginService.login(loginDto);
		Optional<User> isUser = Optional.ofNullable(user);
		if (isUser.isPresent()) {
			responseDto = new ResponseDto(AppConstant.SUCCESS, HttpStatus.OK.value(), AppConstant.LOGIN_SUCCESSFULLY);
		} else {
			responseDto = new ResponseDto(AppConstant.FAILURE, HttpStatus.NOT_FOUND.value(), AppConstant.INVALID_LOGIN);
		}
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	
}
