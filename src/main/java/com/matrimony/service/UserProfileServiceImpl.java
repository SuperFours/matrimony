package com.matrimony.service;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserProfileRepository;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description UserProfileServiceImpl handles the display profile and detailed
 *              profile methods
 * @author Janani.V
 * @since 12-12-2019
 *
 */
=======
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.UserProfileRequestDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserProfileRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileServiceImpl is a class - which we are using to
 *              develop the implementation logic for UserProfiles
 */

>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94
@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	/**
<<<<<<< HEAD
	 * @throws NotFoundException
	 * @description In this method we are getting the user profile Details from the
	 *              User profile
	 * @exception Global Exception handled in this method
	 */
	@Override
	public ProfileResponseDto profileDetail(Integer matrimonyId) throws NotFoundException {
		UserProfile userProfile = userProfileRepository.findByUserMatrimonyIdMatrimonyId(matrimonyId);
		log.info("getting user profile from matrimonyId");
		Optional<UserProfile> isUserProfile = Optional.ofNullable(userProfile);
		if (isUserProfile.isPresent()) {
			ProfileDto profileDto = new ProfileDto();
			ProfileResponseDto profileResponseDto = new ProfileResponseDto();
			BeanUtils.copyProperties(userProfile,profileDto);
			profileResponseDto.setProfile(profileDto);
			return profileResponseDto;
		} else {
			throw new NotFoundException(AppConstant.USER_NOT_FOUND);
		}
	}

=======
	 * @description This method is a used to do implement the logic for profile search, if your a male then it will return female profile and vice versa
	 * @param userMatrimonyId input integer 
	 * @return UserProfileResponseDto - class and it will contain all required parameters
	 */
	@Override
	public UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId) {

		UserProfile userResponse = userProfileRepository.findByUserMatrimonyIdMatrimonyId(userMatrimonyId);
		
		List<UserProfileRequestDto> sserProfileResponseDto=null;
		UserProfileResponseDto response = null;
		List<UserProfile> usersProfiles = null;
		String gender = null;

		if (userResponse !=null) {
			
			gender = userResponse.getGender();

			UserProfile userProfile = new UserProfile();

			if (gender.equalsIgnoreCase(AppConstant.GEMDER_MALE)) {

				userProfile.setGender(AppConstant.GEMDER_FEMALE);
				
				usersProfiles = userProfileRepository.findAllByGender(userProfile.getGender());
				
				sserProfileResponseDto = usersProfiles.stream()
						.map(this::convertEntityToDto).collect(Collectors.toList());

				response = new UserProfileResponseDto();

				response.setMessage(AppConstant.SUCCESS);
				response.setUserProfiles(sserProfileResponseDto);

			} else {
				
				userProfile.setGender(AppConstant.GEMDER_MALE);
				
				usersProfiles = userProfileRepository.findAllByGender(userProfile.getGender());
				
				sserProfileResponseDto = usersProfiles.stream()
						.map(this::convertEntityToDto).collect(Collectors.toList());
				
				response = new UserProfileResponseDto();
				
				response.setMessage(AppConstant.SUCCESS);
				response.setUserProfiles(sserProfileResponseDto);
			}
			
		} else {

			log.info("UserProfileServiceImpl - user profile not found ");
			
			response = new UserProfileResponseDto();
			
			response.setMessage(AppConstant.NO_RECORD_FOUND);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setUserProfiles(sserProfileResponseDto);
		}

		return response;

	}
	
	/**
	 * @description This below method is a used to convert entity to DTO and return the response DTO object
	 * @param UserProfile input object to convert
	 * @return UserProfileRequestDto - class and it will contain all required parameters
	 */
	private UserProfileRequestDto convertEntityToDto(UserProfile userProfile) {
		
		UserProfileRequestDto userProfileRequestDto = new UserProfileRequestDto();
		
		userProfileRequestDto.setUserMatrimonyId(userProfile.getUserMatrimonyId().getMatrimonyId());
		
		BeanUtils.copyProperties(userProfile, userProfileRequestDto);
		
		log.info("Converted entiry and - returning UserProfileRequestDto ");
		
		return userProfileRequestDto;
	}
>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94
}
