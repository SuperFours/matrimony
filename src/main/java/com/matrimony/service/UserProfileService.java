package com.matrimony.service;

import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserProfileResponseDto;

import javassist.NotFoundException;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileService is interface acts as business API
 *              methods for implementation logic
 */

public interface UserProfileService {

	public ProfileResponseDto profileDetail(Integer matrimonyId) throws NotFoundException;

	UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId);

}
