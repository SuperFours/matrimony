package com.matrimony.entity;

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
 * @description - User Profile Interest Entity - We are maintaining the user
 *              interest profiles by given interest and get interest profiles.
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@Setter
@Getter
@Entity
public class UserProfileInterest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "login_matrimony_id")
	private User loginMatrimonyId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "interest_matrimony_id")
	private User interestMatrimonyId;
	private String status;

}
