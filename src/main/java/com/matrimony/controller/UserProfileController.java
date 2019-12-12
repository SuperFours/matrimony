package com.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.service.UserProfileService;

/**
 * @description This UserProfileController is used to get all the profiles with based on opposite gender also individual profiles
 * @author akuthota.raghu
 * @since 12-12-2019
 * */

@RestController
public class UserProfileController {
	
	
	@Autowired
	UserProfileService userProfileService;
	
	
	/**
	 * This below method is used to get the all the profiles based on the opposite gender
	 * @return
	 */
	@GetMapping("users/{userMatrimonyId}/dashbord")
	public ResponseEntity<UserProfileResponseDto> getAllProfiles(@PathVariable Integer userMatrimonyId) {
		
		UserProfileResponseDto userProfileResponseDto = userProfileService.fetchAllProfiles(userMatrimonyId);
		
		return new ResponseEntity<>(userProfileResponseDto, HttpStatus.OK);
	}

}
