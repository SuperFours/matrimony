package com.matrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.dto.UsersResponseDto;
import com.matrimony.service.UserProfileService;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileControllerTest {

	@InjectMocks
	UserProfileController userProfileController;

	@Mock
	UserProfileService userProfileService;

	ProfileResponseDto profileResponseDto = new ProfileResponseDto();

	UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();
	UsersResponseDto usersResponseDto = new UsersResponseDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testprofileDetails() throws NotFoundException {
		Mockito.when(userProfileService.profileDetail(123, 12)).thenReturn(profileResponseDto);
		Integer result = userProfileController.profileDetails(123, 12).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testProfileDetails() {
		Mockito.when(userProfileService.fetchAllProfiles(65421)).thenReturn(userProfileResponseDto);
		Integer result = userProfileController.getAllProfiles(65421).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testGetProfilesInterestedOnMe() {
		usersResponseDto.setMessage(AppConstant.SUCCESS);
		when(userProfileService.fetchProfilesInterestedOnMe(500001)).thenReturn(usersResponseDto);
		ResponseEntity<UsersResponseDto> response = userProfileController.getProfilesInterestedOnMe(500001);
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testGetpreferredProfiles() {
		UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();
		userProfileResponseDto.setMessage(AppConstant.SUCCESS);
		when(userProfileService.findPreferredProfiles(500001)).thenReturn(userProfileResponseDto);
		ResponseEntity<UserProfileResponseDto> response = userProfileController.getpreferredProfiles(500001);
		assertEquals(200, response.getBody().getStatusCode());
	}

}
