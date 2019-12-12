package com.matrimony.service;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserProfileRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserProfileServiceTest {
	
	@InjectMocks
	UserProfileServiceImpl userProfileServiceImpl;

	@Mock
	UserProfileRepository userProfileRepository;
	
	UserProfile userProfile=new UserProfile();
	ProfileResponseDto userProfileResponseDto=new ProfileResponseDto();
	ProfileDto userProfileDto=new ProfileDto();
	
	@Test
	public void testProfileDetail() {
		userProfileDto.setAboutMe("xyz");
		userProfileDto.setAge(12);
	    userProfileDto.setAnnualIncome(1000);
		userProfileDto.setCity("Coimbatore");
		userProfileDto.setDob(LocalDate.of(2014, Month.JANUARY, 1));
		userProfileDto.setEducationDetail("B.E");
		userProfileDto.setEmailAddress("janani@hcl.com");
	    userProfileDto.setImageUrl("Janani");
		userProfileDto.setMaritalStatus("single");	userProfileDto.setName("Janani");
		userProfileDto.setOccupationDetail("Engineer");
	
		userProfileResponseDto.setUserProfile(userProfileDto);
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(12)).thenReturn(userProfile);
		
//		UserProfileResponseDto response=userProfileServiceImpl.profileDetail(userProfileDto);
	}
	
	
	
	
	
}
