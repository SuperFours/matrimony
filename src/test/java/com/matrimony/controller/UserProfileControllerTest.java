package com.matrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;
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

	@Test
	public void testProfileDetailsNotFound() throws NotFoundException {
		Mockito.when(userProfileService.profileDetail(123)).thenReturn(profileResponseDto);
		Integer result = userProfileController.profileDetails(123).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testProfileDetails() {
		Mockito.when(userProfileService.fetchAllProfiles(65421)).thenReturn(userProfileResponseDto);
		Integer result = userProfileController.getAllProfiles(65421).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void getProfilesInterestedOnMe() {
		Mockito.when(userProfileService.fetchAllProfiles(65421)).thenReturn(userProfileResponseDto);
		Integer result = userProfileController.getAllProfiles(65421).getStatusCodeValue();
		assertEquals(200, result);
	}

}
