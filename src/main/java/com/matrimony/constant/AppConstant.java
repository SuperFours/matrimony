package com.matrimony.constant;

/**
 * @description AppConstant is used for only we are maintaining the argcoded
 *              values in the whole application.
 * @author Janani.V
 * @since 12-12-2019
 */
public class AppConstant {

	private AppConstant() {

	}

	// Common Httpstatus success and failure messages.
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAILURE";
	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	// Login
	public static final String USER_PROFILE_ALREADY_EXISTS = "Phone number already exists";
	public static final String LOGIN_SUCCESSFULLY = "User Login Successfully";
	public static final String INVALID_LOGIN = "Invalid Username and Password";

	// User Profile
	public static final String PROFILE_REGISTER_SUCCESSFULLY = "Profile Created Successfully";
	public static final String NO_PROFILES_FOUND = "No Profiles Found.";

	// User Profile Interests
	public static final String INTERESTED = "Interested";
	public static final String INTEREST_SEND_SUCCESSFULLY = "Interest Send Successfully";

}
