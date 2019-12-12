package com.matrimony.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description RegisterRequestDto we can pass the request params for the user
 *              profile creation for matrimony application.
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@Setter
@Getter
public class RegisterRequestDto {

	private String name;
	private Integer age;
	private String gender;
	private String maritialStatus;
	private String emailAddress;
	private String dob;
	private Integer phoneNumber;
	private String password;
	private String city;
	private String aboutMe;
	private String educationDetail;
	private String occupationDetail;
	private Double annualIncome;
}
