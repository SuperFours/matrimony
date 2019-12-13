package com.matrimony.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.InterestRequestDto;
import com.matrimony.dto.RegisterRequestDto;
import com.matrimony.dto.RegisterResponseDto;
import com.matrimony.dto.ResponseDto;
import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
import com.matrimony.entity.UserProfileInterest;
import com.matrimony.exception.UserProfileAlreadyExist;
import com.matrimony.repository.UserProfileInterestRepository;
import com.matrimony.repository.UserProfileRepository;
import com.matrimony.repository.UserRepository;

import javassist.NotFoundException;

/**
 * @description UserServiceImpl class used for user service all methods will be
 *              implements with the proper functionalities.Here we are
 *              registrating the new user
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	UserProfileInterestRepository userProfileInterestRepository;

	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(AppConstant.DATE_FORMAT_PATTERN);

	/**
	 * In this method, we can create a new user profile details based on the
	 * phoneNumber and so getting some informations such as basic informations,
	 * Eductional infomations, annual income details.
	 * 
	 * @param registerRequestDto request params are basic information of users.
	 * @return RegisterResponseDto with sucess and message params.
	 */
	@Override
	public RegisterResponseDto userRegistration(RegisterRequestDto registerRequestDto) {
		logger.debug("user profile registration...");
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		Optional<User> userId = userRepository.findByPhoneNumber(registerRequestDto.getPhoneNumber());
		if (!userId.isPresent()) {
			// User Details
			User user = new User();
			user.setStatus(true);
			BeanUtils.copyProperties(registerRequestDto, user);

			// Save the User Details.
			User savedUser = userRepository.save(user);

			// Set User Profile Details from Dto
			UserProfile userProfile = new UserProfile();
			BeanUtils.copyProperties(registerRequestDto, userProfile);
			userProfile.setUserMatrimonyId(savedUser);
			//userProfile.setOccupationDetail(registerRequestDto.getOccupationDetail());

			// Convert String to LocalDate
			LocalDate dob = LocalDate.parse(registerRequestDto.getDob(), dateFormat);
			userProfile.setDob(dob);

			// Calculate the user age based on the dob.
			Integer age = calculateAge(dob, LocalDate.now());
			userProfile.setAge(age);

			// Save the user profile details.
			userProfileRepository.save(userProfile);

			registerResponseDto.setMatrimonyId(user.getMatrimonyId());
			registerResponseDto.setMessage(AppConstant.PROFILE_REGISTER_SUCCESSFULLY);
		} else {
			throw new UserProfileAlreadyExist(AppConstant.USER_PROFILE_ALREADY_EXISTS);
		}

		return registerResponseDto;
	}

	/**
	 * send interest with other user profiles.
	 * 
	 * @param matrimonyId
	 * @param interestRequestDto request param is interested matrimony id.
	 * @return ResponseDto params for sucess message along with status code.
	 * @throws NotFoundException
	 */
	@Override
	public ResponseDto sendInterest(Integer matrimonyId, InterestRequestDto interestRequestDto)
			throws NotFoundException {
		logger.info("send interest...");
		ResponseDto responseDto = new ResponseDto();
		Optional<User> interestedUserId = userRepository.findByMatrimonyId(interestRequestDto.getInterestMatrimonyId());
		Optional<User> loginUserId = userRepository.findByMatrimonyId(matrimonyId);
		// Check the interested Matrimony Id and Login matrimony id.
		if (interestedUserId.isPresent() && loginUserId.isPresent()) {
			UserProfileInterest userProfileInterest = new UserProfileInterest();
			userProfileInterest.setInterestMatrimonyId(interestedUserId.get());
			userProfileInterest.setLoginMatrimonyId(loginUserId.get());
			userProfileInterest.setStatus(AppConstant.INTERESTED);

			userProfileInterestRepository.save(userProfileInterest);
			responseDto.setMessage(AppConstant.INTEREST_SEND_SUCCESSFULLY);
		} else {
			throw new NotFoundException(AppConstant.NO_PROFILES_FOUND);
		}

		return responseDto;
	}

	/**
	 * calculate the age based on date of birth with current date.
	 * 
	 * @param dob
	 * @param currentDate
	 * @return Integer value of age(years).
	 */
	private static Integer calculateAge(LocalDate dob, LocalDate currentDate) {
		logger.info("calculate the age.");
		if ((dob != null) && (currentDate != null)) {
			return Period.between(dob, currentDate).getYears();
		} else {
			return 0;
		}
	}
}
