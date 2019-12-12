package com.matrimony.service;

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

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

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
	 * @param userProfile
	 * @return UserProfileRequestDto - class and it will contain all required parameters
	 * @description This below method is a used to convert entity to DTO and return the response DTO object
	 */
	private UserProfileRequestDto convertEntityToDto(UserProfile userProfile) {
		
		UserProfileRequestDto userProfileRequestDto = new UserProfileRequestDto();
		
		userProfileRequestDto.setUserMatrimonyId(userProfile.getUserMatrimonyId().getMatrimonyId());
		
		BeanUtils.copyProperties(userProfile, userProfileRequestDto);
		
		log.info("Converted entiry and - returning UserProfileRequestDto ");
		
		return userProfileRequestDto;
	}
}
