package com.matrimony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserProfileRepository;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileServiceTest {
	
	@InjectMocks
	UserProfileServiceImpl  userProfileServiceImpl;
	
	@Mock
	UserProfileRepository userProfileRepository;
	
	UserProfile userProfile=new UserProfile();
	ProfileDto profileDto = new ProfileDto();
	
	
	@Test
	public void testProfileDetail() throws NotFoundException {
		userProfile.setName("Moorthy");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(userProfile);
		ProfileResponseDto profileResponseDto = userProfileServiceImpl.profileDetail(123);
		assertEquals("Moorthy", profileResponseDto.getProfile().getName());
	}
	
	
	@Test(expected = NotFoundException.class)
	public void testProfileDetails() throws NotFoundException {
		userProfile.setName("Moorthy");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(null)).thenReturn(userProfile);
		ProfileResponseDto profileResponseDto = userProfileServiceImpl.profileDetail(123);
		assertEquals("Moorthy", profileResponseDto.getProfile().getName());
	}
	
}
