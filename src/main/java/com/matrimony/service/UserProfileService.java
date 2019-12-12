package com.matrimony.service;

<<<<<<< HEAD
import com.matrimony.dto.ProfileResponseDto;

import javassist.NotFoundException;

public interface UserProfileService {
	
	public ProfileResponseDto profileDetail(Integer matrimonyId) throws NotFoundException;
=======
import com.matrimony.dto.UserProfileResponseDto;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 * @description This UserProfileService is interface acts as business API methods for implementation logic */
 
public interface UserProfileService {

	UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId);
>>>>>>> 43604fc8ce50c6bbf6e301aae80eb1765af88a94

}
