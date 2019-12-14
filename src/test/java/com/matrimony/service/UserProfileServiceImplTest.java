package com.matrimony.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.UserProfileRequestDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.dto.UsersResponseDto;
import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
import com.matrimony.entity.UserProfileInterest;
import com.matrimony.repository.UserProfileInterestRepository;
import com.matrimony.repository.UserProfileRepository;

import javassist.NotFoundException;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserProfileServiceImplTest {

	@InjectMocks
	UserProfileServiceImpl userProfileServiceImpl;

	@Mock
	UserProfileRepository userProfileRepository;
	@Mock
	UserProfileInterestRepository userProfileInterestRepository;

	UserProfileResponseDto response = new UserProfileResponseDto();
	List<UserProfile> userProfiles = new ArrayList<>();
	UserProfileInterest userProfileInterest = new UserProfileInterest();
	List<UserProfileInterest> userProfileInterests = new ArrayList<>();
	UserProfile userProfile = new UserProfile();
    User user = new User();
    UserProfileRequestDto profiles = new UserProfileRequestDto();
	@Before
	public void init() {
		user.setMatrimonyId(500001);
		userProfile.setGender("female");
		userProfiles.add(userProfile);
		userProfileInterest.setId(1);
		userProfileInterests.add(userProfileInterest);
		
		profiles.setGender(AppConstant.GENDER_MALE);
		profiles.setAnnualIncome("40000.00");
		profiles.setAge(27);
		profiles.setEmailAddress("moorthy127@gmail.com");
		profiles.setAboutMe("About Me");
		profiles.setCity("Bangalore");
	}

	ProfileDto profileDto = new ProfileDto();

	@Test(expected = NotFoundException.class)
	public void testProfileDetailException() throws NotFoundException {
		userProfile.setName("Moorthy");
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(Mockito.any())).thenReturn(null);
		assertNull(userProfileServiceImpl.profileDetail(1, 1));

	}

	@Test
	public void testProfileDetailNull() throws NotFoundException {

		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(Mockito.any()))
				.thenReturn(new UserProfile());
		Mockito.when(userProfileInterestRepository
				.findByInterestMatrimonyIdMatrimonyIdAndLoginMatrimonyIdMatrimonyId(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.ofNullable(null));
		assertNotNull(userProfileServiceImpl.profileDetail(1, 1));

	}

	@Test
	public void testProfileDetailSuccess() throws NotFoundException {

		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(Mockito.any()))
				.thenReturn(new UserProfile());
		Mockito.when(userProfileInterestRepository
				.findByInterestMatrimonyIdMatrimonyIdAndLoginMatrimonyIdMatrimonyId(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.ofNullable(new UserProfileInterest()));
		assertNotNull(userProfileServiceImpl.profileDetail(1, 1));

	}

	@Test
	public void testFetchAllProfiles() {
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(userProfile);
		Mockito.when(userProfileRepository.findAllByGender(userProfile.getGender())).thenReturn(userProfiles);
		UserProfileResponseDto response = userProfileServiceImpl.fetchAllProfiles(123);
		assertEquals(AppConstant.SUCCESS, response.getMessage());
	}

	@Test
	public void testFetchAllProfilesGender() {
		userProfile.setGender(AppConstant.GENDER_MALE);
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(userProfile);
		Mockito.when(userProfileRepository.findAllByGender(userProfile.getGender())).thenReturn(null);

		List<UserProfileRequestDto> sserProfileResponseDto = new ArrayList<>();
		sserProfileResponseDto.add(profiles);
		
		response.setMessage(AppConstant.SUCCESS);
		response.setProfiles(sserProfileResponseDto);
		UserProfileResponseDto expected = userProfileServiceImpl.fetchAllProfiles(123);
		assertEquals(AppConstant.SUCCESS, expected.getMessage());
	}

	@Test
	public void testFetchAllProfilesNull() {
		Mockito.when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(123)).thenReturn(null);
		Mockito.when(userProfileRepository.findAllByGender(userProfile.getGender())).thenReturn(null);
		UserProfileResponseDto response = userProfileServiceImpl.fetchAllProfiles(123);
		assertEquals(AppConstant.NO_RECORD_FOUND, response.getMessage());
	}

	@Test
	public void testFetchProfilesInterestedOnMe() {
		
		User loginUser = new User();
		loginUser.setMatrimonyId(500001);

		userProfile.setUserMatrimonyId(loginUser);
		loginUser.setUserProfile(userProfile);
		userProfileInterest.setLoginMatrimonyId(loginUser);
		
		List<UserProfileInterest> userProfileInterests = new ArrayList<>();
		userProfileInterests.add(userProfileInterest);
		when(userProfileInterestRepository.findAllByInterestMatrimonyIdMatrimonyId(50001))
				.thenReturn(userProfileInterests);
		UsersResponseDto response = userProfileServiceImpl.fetchProfilesInterestedOnMe(50001);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	
	@Test
	public void testFetchProfilesInterestedOnMeZero() {
		List<UserProfileInterest> userProfileInterests = new ArrayList<>();
		when(userProfileInterestRepository.findAllByInterestMatrimonyIdMatrimonyId(50001))
				.thenReturn(userProfileInterests);
		UsersResponseDto response = userProfileServiceImpl.fetchProfilesInterestedOnMe(50001);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
	
	@Test
	public void testFindPreferredProfiles() {
		when(userProfileRepository.findByUserMatrimonyIdMatrimonyId(500001)).thenReturn(userProfile);
		when(userProfileRepository.findAll()).thenReturn(userProfiles);
		UserProfileResponseDto response = userProfileServiceImpl.findPreferredProfiles(500001);
		assertEquals(AppConstant.SUCCESS, response.getMessage());
	}

}
