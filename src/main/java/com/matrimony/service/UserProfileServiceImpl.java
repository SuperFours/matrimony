package com.matrimony.service;

import java.util.ArrayList;
/**
 * @description UserProfileServiceImpl handles the display profile and detailed
 *              profile methods
 * @author Janani.V
 * @since 12-12-2019
 *
 */
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ProfileDto;
import com.matrimony.dto.ProfileResponseDto;
import com.matrimony.dto.UserDto;
import com.matrimony.dto.UserProfileRequestDto;
import com.matrimony.dto.UserProfileResponseDto;
import com.matrimony.dto.UsersResponseDto;
import com.matrimony.entity.UserProfile;
import com.matrimony.entity.UserProfileInterest;
import com.matrimony.repository.PreferredProfilesRepository;
import com.matrimony.repository.UserProfileInterestRepository;
import com.matrimony.repository.UserProfileRepository;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;

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

	@Autowired
	UserProfileInterestRepository userProfileInterestRepository;

	@Autowired
	PreferredProfilesRepository preferredProfilesRepository;

	/**
	 * @throws NotFoundException
	 * @description In this method we are getting the user profile Details from the
	 *              User profile
	 * @exception Global Exception handled in this method
	 */
	@Override
	public ProfileResponseDto profileDetail(Integer matrimonyId, Integer loginId) throws NotFoundException {
		UserProfile userProfile = userProfileRepository.findByUserMatrimonyIdMatrimonyId(matrimonyId);
		log.info("getting user profile from matrimonyId");
		Optional<UserProfile> isUserProfile = Optional.ofNullable(userProfile);
		if (isUserProfile.isPresent()) {
			ProfileDto profileDto = new ProfileDto();
			ProfileResponseDto profileResponseDto = new ProfileResponseDto();
			BeanUtils.copyProperties(userProfile, profileDto);

			Optional<UserProfileInterest> userInterest = userProfileInterestRepository
					.findByInterestMatrimonyIdMatrimonyIdAndLoginMatrimonyIdMatrimonyId(matrimonyId, loginId);
			if (userInterest.isPresent()) {
				profileDto.setStatus(userInterest.get().getStatus());
			}
			profileResponseDto.setProfile(profileDto);
			return profileResponseDto;
		} else {
			throw new NotFoundException(AppConstant.USER_NOT_FOUND);
		}
	}

	/**
	 * @description This method is a used to do implement the logic for profile
	 *              search, if your a male then it will return female profile and
	 *              vice versa
	 * @param userMatrimonyId input integer
	 * @return UserProfileResponseDto - class and it will contain all required
	 *         parameters
	 */
	@Override
	public UserProfileResponseDto fetchAllProfiles(Integer userMatrimonyId) {

		UserProfile userResponse = userProfileRepository.findByUserMatrimonyIdMatrimonyId(userMatrimonyId);

		Optional<UserProfile> usersProfile = Optional.ofNullable(userResponse);

		List<UserProfileRequestDto> sserProfileResponseDto = null;
		UserProfileResponseDto response = null;
		List<UserProfile> usersProfiles = null;
		String gender = null;

		if (usersProfile.isPresent()) {
			gender = userResponse.getGender();
			UserProfile userProfile = new UserProfile();
			if (gender.equalsIgnoreCase(AppConstant.GEMDER_MALE)) {
				userProfile.setGender(AppConstant.GEMDER_FEMALE);
				usersProfiles = userProfileRepository.findAllByGender(userProfile.getGender());
				sserProfileResponseDto = usersProfiles.stream().map(this::convertEntityToDto)
						.collect(Collectors.toList());
				response = new UserProfileResponseDto();
				response.setMessage(AppConstant.SUCCESS);
				response.setProfiles(sserProfileResponseDto);

			} else {
				userProfile.setGender(AppConstant.GEMDER_MALE);
				usersProfiles = userProfileRepository.findAllByGender(userProfile.getGender());
				sserProfileResponseDto = usersProfiles.stream().map(this::convertEntityToDto)
						.collect(Collectors.toList());
				response = new UserProfileResponseDto();
				response.setMessage(AppConstant.SUCCESS);
				response.setProfiles(sserProfileResponseDto);
			}

		} else {

			log.info("UserProfileServiceImpl - user profile not found ");

			response = new UserProfileResponseDto();

			response.setMessage(AppConstant.NO_RECORD_FOUND);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setProfiles(sserProfileResponseDto);
		}

		return response;

	}

	/**
	 * @description This below method is a used to convert entity to DTO and return
	 *              the response DTO object
	 * @param UserProfile input object to convert
	 * @return UserProfileRequestDto - class and it will contain all required
	 *         parameters
	 */
	private UserProfileRequestDto convertEntityToDto(UserProfile userProfile) {

		UserProfileRequestDto userProfileRequestDto = new UserProfileRequestDto();

		userProfileRequestDto.setUserMatrimonyId(userProfile.getUserMatrimonyId().getMatrimonyId());

		BeanUtils.copyProperties(userProfile, userProfileRequestDto);

		log.info("Converted entiry and - returning UserProfileRequestDto ");

		return userProfileRequestDto;
	}

	/**
	 * @description this method will fetch all the interested users on me
	 * @param UserMatrimonyId integer
	 * @return UsersResponseDto object will contains set of properties
	 */
	@Override
	public UsersResponseDto fetchProfilesInterestedOnMe(Integer userMatrimonyId) {

		List<UserProfileInterest> interestedUsers = null;
		List<UserDto> userDtoResponse = null;

		UsersResponseDto usersResponseDto = null;

		interestedUsers = userProfileInterestRepository.findAllByInterestMatrimonyIdMatrimonyId(userMatrimonyId);

		if (interestedUsers != null && interestedUsers.size() > AppConstant.ZERO) {

			userDtoResponse = interestedUsers.stream().map(this::convertUserEntityToDto).collect(Collectors.toList());

			usersResponseDto = new UsersResponseDto();
			usersResponseDto.setMessage(AppConstant.SUCCESS);
			usersResponseDto.setStatusCode(HttpStatus.OK.value());
			usersResponseDto.setInterestedProfiles(userDtoResponse);
		} else {
			log.info("Interested User Not ound");
			usersResponseDto = new UsersResponseDto();
			usersResponseDto.setMessage(AppConstant.NO_RECORD_FOUND);
			usersResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			usersResponseDto.setInterestedProfiles(userDtoResponse);
		}

		return usersResponseDto;
	}

	/**
	 * @description This below method is a used to convert entity to DTO and return
	 *              the response DTO object
	 * @param UserProfile input object to convert
	 * @return UserProfileRequestDto - class and it will contain all required
	 *         parameters
	 */
	private UserDto convertUserEntityToDto(UserProfileInterest userProfileInterest) {

		UserDto userDto = new UserDto();

		log.info("Converted user entiry and - returning UserDto ");
		UserProfile loginProfile = userProfileInterest.getLoginMatrimonyId().getUserProfile();
		userDto.setUserMatrimonyId(loginProfile.getUserMatrimonyId().getMatrimonyId());
		userDto.setCity(loginProfile.getCity());
		userDto.setAge(loginProfile.getAge());
		userDto.setEducationDetail(loginProfile.getEducationDetail());
		userDto.setName(loginProfile.getName());
		userDto.setImageUrl(loginProfile.getImageUrl());

		userDto.setStatus(userProfileInterest.getStatus());

		return userDto;
	}

	/**
	 * @description This method will fetch all the preferred match profiles
	 * @param userMatrimonyId Integer
	 * @return List of UserProfile
	 */
	public List<UserProfile> findPreferredProfiles(Integer userMatrimonyId) {

		List<UserProfile> userProfiles = new ArrayList<>();
		UserProfile userProfileResponse = userProfileRepository.findByUserMatrimonyIdMatrimonyId(userMatrimonyId);

		Optional<UserProfile> isUsersProfile = Optional.ofNullable(userProfileResponse);

		if (isUsersProfile.isPresent()) {

			userProfiles = preferredProfilesRepository.findAll(new Specification<UserProfile>() {

				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<UserProfile> userProfile, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					if (userProfile != null) {

						predicates.add(criteriaBuilder.or(
								
								criteriaBuilder.equal(userProfile.get("partnerCity"),
										isUsersProfile.get().getPartnerCity()),

								criteriaBuilder.equal(userProfile.get("partnerEducation"),
										isUsersProfile.get().getPartnerEducation()),

								criteriaBuilder.equal(userProfile.get("partnerOccupation"),
										isUsersProfile.get().getPartnerOccupation()),

								criteriaBuilder.equal(userProfile.get("foodHabit"),
										isUsersProfile.get().getFoodHabit()),

								criteriaBuilder.equal(userProfile.get("partnerFood"),
										isUsersProfile.get().getPartnerFood())

						));
					}
					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});

			userProfiles = userProfiles.stream()
					.filter(userProfile -> !userProfile.getId().equals(isUsersProfile.get().getId()))
					.collect(Collectors.toList());

		}
		return userProfiles;
	}
}
