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
import com.matrimony.dto.LoginResponseDto;
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
	 * 
	 * @param userDto -> params are userId and password.
	 * @return response is success or failure
	 */

	@PostMapping
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
		logger.info("user login checks...");
		LoginResponseDto responseDto = new LoginResponseDto();
		User user = loginService.login(loginDto);
		Optional<User> isUser = Optional.ofNullable(user);
		if (isUser.isPresent()) {
			responseDto.setMatrimonyId(user.getMatrimonyId());
			responseDto.setUserName(user.getUserProfile().getName());
			responseDto.setMessage(AppConstant.LOGIN_SUCCESSFULLY);
			responseDto.setStatus(AppConstant.SUCCESS);
			responseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			responseDto.setMessage(AppConstant.INVALID_LOGIN);
			responseDto.setStatus(AppConstant.FAILURE);
			responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
