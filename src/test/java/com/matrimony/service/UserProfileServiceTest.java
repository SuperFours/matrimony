package com.matrimony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserProfileRepository;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserProfileServiceTest {
	
	@InjectMocks
	UserProfileServiceImpl  userProfileServiceImpl;
	
	@Mock
	UserProfileRepository userProfileRepository;
	
	UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();
	
	List<UserProfile> userProfiles = new ArrayList<>();
	
	UserProfile userProfile=new UserProfile();
	
	@Before
	public void init() {
		userProfile.setGender("female");
		
		userProfiles.add(userProfile);
	}
	
	
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
		userProfileResponseDto.setMessage("Success");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(12312)).thenReturn(userProfile);
		ProfileResponseDto profileResponseDto = userProfileServiceImpl.profileDetail(123);
		assertEquals("Moorthy", profileResponseDto.getProfile().getName());
	}
	
	
	@Test
	public void testFetchProfilesInterestedOnMe() throws NotFoundException {
		userProfile.setName("Moorthy");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(userProfile);
		ProfileResponseDto profileResponseDto = userProfileServiceImpl.profileDetail(123);
		assertEquals("Moorthy", profileResponseDto.getProfile().getName());
	}
	
	@Test
	public void testFetchAllProfiles() throws NotFoundException {
		userProfile.setName("Moorthy");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(userProfile);
		ProfileResponseDto profileResponseDto = userProfileServiceImpl.profileDetail(123);
		assertEquals("Moorthy", profileResponseDto.getProfile().getName());
	}
	
}