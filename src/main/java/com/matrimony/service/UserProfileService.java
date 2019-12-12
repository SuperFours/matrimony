package com.matrimony.service;

import com.matrimony.dto.UserProfileResponseDto;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileService is interface acts as business API methods for implementation logic */
 
public interface UserProfileService {

	UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId);

}
