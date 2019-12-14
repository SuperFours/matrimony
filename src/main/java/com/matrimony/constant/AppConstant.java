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
	public static final String NO_RECORD_FOUND = "No Record Found";

	// Login
	public static final String USER_PROFILE_ALREADY_EXISTS = "Phone number already exists";
	public static final String LOGIN_SUCCESSFULLY = "User Login Successfully";
	public static final String INVALID_LOGIN = "Invalid Username and Password";
	
	//User Profile
	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";

	// User Profile
	public static final String PROFILE_REGISTER_SUCCESSFULLY = "Profile Created Successfully";
	public static final String NO_PROFILES_FOUND = "No Profiles Found.";

	// User Profile Interests
	public static final String INTERESTED = "Interested";
	public static final String INTEREST_SEND_SUCCESSFULLY = "Interest Send Successfully";

    // User profile Details
	public static final String USER_NOT_FOUND = "User Profile Not found";
	public static final Integer ZERO = 0;
	
	//Search Partner Profile Preference
	public static final String PARTNER_CITY = "partnerCity";
	public static final String PARTNER_EDUCATION = "partnerEducation";
	public static final String PARTNER_OCCUPATION = "partnerOccupation";
	public static final String PARTNER_FOOD_HABIT = "partnerFood";

}
