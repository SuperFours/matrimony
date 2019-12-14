package com.matrimony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.InterestRequestDto;
import com.matrimony.dto.RegisterRequestDto;
import com.matrimony.dto.RegisterResponseDto;
import com.matrimony.dto.ResponseDto;
import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
import com.matrimony.entity.UserProfileInterest;
import com.matrimony.exception.UserProfileAlreadyExist;
import com.matrimony.repository.UserProfileInterestRepository;
import com.matrimony.repository.UserProfileRepository;
import com.matrimony.repository.UserRepository;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserProfileRepository userProfileRepository;

	@Mock
	UserProfileInterestRepository userProfileInterestRepository;
	User user = new User();
	UserProfile userProfile = new UserProfile();
	RegisterRequestDto registerRequestDto = new RegisterRequestDto();
	InterestRequestDto interestRequestDto = new InterestRequestDto();
	UserProfileInterest userProfileInterest = new UserProfileInterest();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		registerRequestDto.setPhoneNumber("8675958381");
		registerRequestDto.setDob("1992-04-23");
	}

	@Test
	public void testUserRegistration() {
		when(userRepository.findByPhoneNumber("7845726364")).thenReturn(Optional.of(user));
		when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
		RegisterResponseDto response = userServiceImpl.userRegistration(registerRequestDto);
		assertEquals(AppConstant.PROFILE_REGISTER_SUCCESSFULLY, response.getMessage());
	}

	@Test(expected = NotFoundException.class)
	public void testSendInterestForException() throws NotFoundException {
		interestRequestDto.setInterestMatrimonyId(500002);
		user.setMatrimonyId(500001);
		when(userRepository.findByMatrimonyId(50001)).thenReturn(Optional.of(user));
		when(userProfileInterestRepository.save(userProfileInterest)).thenReturn(userProfileInterest);
		userServiceImpl.sendInterest(50001, interestRequestDto);
	}

	@Test(expected = UserProfileAlreadyExist.class)
	public void testUserRegistrationForException() {
		user.setMatrimonyId(500001);
		when(userRepository.findByPhoneNumber(registerRequestDto.getPhoneNumber())).thenReturn(Optional.of(user));
		userServiceImpl.userRegistration(registerRequestDto);
	}

	@Test
	public void testSendInterest() throws NotFoundException {
		interestRequestDto.setInterestMatrimonyId(500001);
		user.setMatrimonyId(500001);

		User loginUser = new User();
		loginUser.setMatrimonyId(500002);
		when(userRepository.findByMatrimonyId(interestRequestDto.getInterestMatrimonyId()))
				.thenReturn(Optional.of(user));
		when(userRepository.findByMatrimonyId(500002)).thenReturn(Optional.of(loginUser));
		when(userProfileInterestRepository.save(userProfileInterest)).thenReturn(userProfileInterest);
		ResponseDto response = userServiceImpl.sendInterest(500001, interestRequestDto);
		assertEquals(AppConstant.INTEREST_SEND_SUCCESSFULLY, response.getMessage());
	}
}
