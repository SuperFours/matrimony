package com.matrimony.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
	
	
	
	private String name;
	private String maritialStatus;
	private LocalDate dob;
	private Integer age;
	private String city;
	private String emailAddress;
	private String educationDetail;
	private String occupationDetail;
	private Double annualIncome;
	private String aboutMe;
	private String imageUrl;
	private String status;

}
