package com.matrimony.service;

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
@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	/**
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

}
