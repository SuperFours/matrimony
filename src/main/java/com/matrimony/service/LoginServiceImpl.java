package com.matrimony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.dto.LoginDto;
import com.matrimony.entity.User;
import com.matrimony.repository.UserRepository;

/**
 * UserServiceImpl class - Here we are implementing the login service interface methods such as login.
 * @author Govindasamy C
 * Created Date - 27-11-2019
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	/**
	 * we can check here the user login by giving userId and password
	 * @param userDto -> params are userId and password
	 * return User object
	 */
	@Override
	public User login(LoginDto loginDto) {
		return userRepository.findByPhoneNumberAndPassword(loginDto.getPhoneNumber(), loginDto.getPassword());
	}
}
