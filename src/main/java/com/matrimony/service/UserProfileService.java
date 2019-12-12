package com.matrimony.service;

import com.matrimony.dto.ProfileResponseDto;

import javassist.NotFoundException;

public interface UserProfileService {
	
	public ProfileResponseDto profileDetail(Integer matrimonyId) throws NotFoundException;

}
