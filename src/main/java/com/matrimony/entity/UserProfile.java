package com.matrimony.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * @description - User Profile Entity - We are maintaining the some more user
 *              values for user profile related details. .
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@Setter
@Getter
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_matrimony_id")
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
