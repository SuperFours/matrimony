package com.matrimony.service;

import java.util.List;

import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.dto.UsersResponseDto;
import com.matrimony.entity.UserProfile;

import javassist.NotFoundException;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileService is interface acts as business API
 *              methods for implementation logic
 */

public interface UserProfileService {

	public ProfileResponseDto profileDetail(Integer matrimonyId, Integer loginId) throws NotFoundException;

	UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId);
	
	UsersResponseDto fetchProfilesInterestedOnMe(Integer userMatrimonyId);

	public List<UserProfile> findPreferredProfiles(Integer userMatrimonyId);

}
