package com.matrimony.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_matrimony_id")
	private User userMatrimonyId;
	private String name;
	private Integer age;
	private String gender;
	private String maritalStatus;
	private String emailAddress;
	private LocalDate dob;
	private String phoneNumber;
	private String city;
	private String aboutMe;
	private String educationDetail;
	private String occupationDetail;
	private Double annualIncome;
	private String imageUrl;

}
