package com.matrimony.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @description - User Entity - We are maintaining the user details in the user table.
 * @author Govindasamy.C
 * @since
 */
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "my_sequence", sequenceName= "dbsequence",
	                   initialValue = 10000, allocationSize = 10)
	private String matrimonyId;
	private Integer phoneNumber;
	private String password;
	private boolean status;
}
