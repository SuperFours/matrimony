package com.matrimony.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequestDto {
	
	private Integer userMatrimonyId;
	private String name;
	private Integer age;
	private String gender;
	private String maritialStatus;
	private String emailAddress;
	private LocalDate dob;
	private Integer mobileNumber;
	private String city;
	private String aboutMe;
	private String educationDetail;
	private String occupationDetail;
	private String annualIncome;
	private String imageUrl;

}
