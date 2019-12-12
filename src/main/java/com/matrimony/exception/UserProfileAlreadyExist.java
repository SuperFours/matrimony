package com.matrimony.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.matrimony.constant.AppConstant;

/**
 * UserProfileAlreadyExist - we are handled here the user account already exists
 * exception.
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since - 12-12-2019
 */

@ResponseStatus(value = HttpStatus.OK)
public class UserProfileAlreadyExist extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserProfileAlreadyExist() {
		super();
	}

	public UserProfileAlreadyExist(String userexist) {		
		super(AppConstant.USER_PROFILE_ALREADY_EXISTS);
	}

}
