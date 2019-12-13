package com.matrimony.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	@NotBlank(message = "name should be mandatory")
	private String name;
	
	@NotBlank(message = "phoneNumber should be mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid phoneNumber.")
	private String phoneNumber;
	private Integer age;
	private String gender;
	private String maritialStatus;
	private String emailAddress;
	private String dob;
	private String password;
	private String city;
	private String aboutMe;
	private String educationDetail;
	private String imageUrl;
	private String occupationDetail;
	private Double annualIncome;
}
