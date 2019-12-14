package com.matrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.InterestRequestDto;
import com.matrimony.dto.RegisterRequestDto;
import com.matrimony.dto.RegisterResponseDto;
import com.matrimony.dto.ResponseDto;
import com.matrimony.service.UserService;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	RegisterRequestDto registerRequestDto = new RegisterRequestDto();
	RegisterResponseDto registerResponseDto = new RegisterResponseDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		registerRequestDto.setPhoneNumber("8675958381");
	}

	@Test
	public void testUserRegistration() {
		registerResponseDto.setMatrimonyId(500001);
		registerResponseDto.setMessage(AppConstant.PROFILE_REGISTER_SUCCESSFULLY);
		when(userService.userRegistration(registerRequestDto)).thenReturn(registerResponseDto);
		ResponseEntity<RegisterResponseDto> response = userController.userRegistration(registerRequestDto);
		assertEquals(HttpStatus.OK.value(), response.getBody().getStatusCode());
	}

	@Test
	public void testSendInterest() throws NotFoundException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(AppConstant.PROFILE_REGISTER_SUCCESSFULLY);
		responseDto.setStatus(AppConstant.SUCCESS);
		InterestRequestDto interestRequestDto = new InterestRequestDto();
		interestRequestDto.setInterestMatrimonyId(500002);
		when(userService.sendInterest(500001, interestRequestDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = userController.sendInterest(500001, interestRequestDto);
		assertEquals(HttpStatus.OK.value(), response.getBody().getStatusCode());
	}

	@Test
	public void testSendInterestForNegative() throws NotFoundException {
		ResponseDto responseDto = new ResponseDto();
		InterestRequestDto interestRequestDto = new InterestRequestDto();
		interestRequestDto.setInterestMatrimonyId(500002);
		when(userService.sendInterest(500001, interestRequestDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = userController.sendInterest(500001, interestRequestDto);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
	}

}
