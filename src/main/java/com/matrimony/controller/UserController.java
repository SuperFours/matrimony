package com.matrimony.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.dto.InterestRequestDto;
import com.matrimony.dto.RegisterRequestDto;
import com.matrimony.dto.RegisterResponseDto;
import com.matrimony.dto.ResponseDto;
import com.matrimony.service.UserService;

import javassist.NotFoundException;

/**
 * @description - User Controller can handles the all the user functionalities
 *              such as user profile creation and User Profile Interest
 *              Controller, we have here implemented the who are all interested
 *              with other user profiles and showed the user profiles by own
 *              login user.
 * @author Govindasamy.C
 * @since 12-12-2019
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	UserService userService;

	/**
	 * In this method, we can create a new user profile details based on the
	 * phoneNumber and so getting some informations such as basic informations,
	 * Eductional infomations, annual income details.
	 * 
	 * @param registerRequestDto request params are basic information of users.
	 * @return RegisterResponseDto with sucess and message params.
	 */
	@PostMapping("/registrations")
	public ResponseEntity<RegisterResponseDto> userRegistration(
			@Valid @RequestBody RegisterRequestDto registerRequestDto) {
		logger.info("user profile registration...");
		RegisterResponseDto response = userService.userRegistration(registerRequestDto);
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * send interest with other user profiles.
	 * 
	 * @param matrimonyId
	 * @param interestRequestDto request param is interested matrimony id.
	 * @return ResponseDto params for sucess message along with status code.
	 * @throws NotFoundException
	 */
	@PostMapping("/{matrimonyId}")
	public ResponseEntity<ResponseDto> sendInterest(@PathVariable Integer matrimonyId,
			@RequestBody InterestRequestDto interestRequestDto) throws NotFoundException {
		logger.info("send interest by matrimonyID...");
		ResponseDto responseDto = userService.sendInterest(matrimonyId, interestRequestDto);
		Optional<String> response = Optional.ofNullable(responseDto.getMessage());
		if (response.isPresent()) {
			responseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
