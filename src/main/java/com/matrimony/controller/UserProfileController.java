package com.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.dto.UsersResponseDto;
import com.matrimony.service.UserProfileService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description UserProfileController- it handles the userProfile api's it has
 *              the all user profiles, get profile user detail, shows the
 *              interested profiles based on user profile and shows the user
 *              profiles by user preferred params are age, city, food habits and
 *              eductional detail. methods
 * @author Janani.v
 * @since 12-12-2019
 * @version V1
 *
 */
@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserProfileController {

	@Autowired
	UserProfileService userProfileService;

	/**
	 * @description In this method used for getting user profile Details . If user
	 *              profile is not found it will throw the not found exception
	 * @param userMatrimonyId
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/users/{matrimonyId}/{loginId}")
	public ResponseEntity<ProfileResponseDto> profileDetails(@PathVariable Integer matrimonyId,
			@PathVariable Integer loginId) throws NotFoundException {
		log.info("getting the user profile detail.");
		ProfileResponseDto profileResponseDto = userProfileService.profileDetail(matrimonyId, loginId);
		profileResponseDto.setMessage(AppConstant.SUCCESS);
		profileResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(profileResponseDto, HttpStatus.OK);
	}

	/**
	 * This below method is used to get the all the profiles based on the opposite
	 * gender
	 * 
	 * @param userMatrimonyId integer
	 * @return UserProfileResponseDto
	 */
	@GetMapping("users/{userMatrimonyId}/dashboard")
	public ResponseEntity<UserProfileResponseDto> getAllProfiles(@PathVariable Integer userMatrimonyId) {
		log.info("get all the user profile.");
		UserProfileResponseDto userProfileResponseDto = userProfileService.fetchAllProfiles(userMatrimonyId);
		userProfileResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(userProfileResponseDto, HttpStatus.OK);
	}

	/**
	 * @description This below method is used to fetch all the interested profiles
	 * @param userMatrimonyId integer
	 * @return UsersResponseDto object contains set of dependent properties
	 */
	@GetMapping("/{userMatrimonyId}/interests")
	public ResponseEntity<UsersResponseDto> getProfilesInterestedOnMe(@PathVariable Integer userMatrimonyId) {
		log.info("interested users info response sent back to UI ");
		UsersResponseDto usersResponseDto = userProfileService.fetchProfilesInterestedOnMe(userMatrimonyId);
		usersResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(usersResponseDto, HttpStatus.OK);
	}

	/**
	 * @description This method is used to get the all the matched profiles and
	 *              filter based on the highest matching and return it to end user
	 * @author akuthota.raghu
	 * @param
	 * @return
	 *
	 */
	@GetMapping("/{userMatrimonyId}/preferredprofiles")
	public ResponseEntity<UserProfileResponseDto> getpreferredProfiles(@PathVariable Integer userMatrimonyId) {
		log.info("get all the preferred profiles based on user profile.");
		UserProfileResponseDto responseDto = userProfileService.findPreferredProfiles(userMatrimonyId);
		responseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
