package com.matrimony.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description - User Entity - We are maintaining the user details in the user
 *              table.
 * @author Govindasamy.C
 * @since
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
<<<<<<< HEAD
@SequenceGenerator(name = "matrimonyId", allocationSize= 1, initialValue = 50000)
public class User {
=======
public class User implements Serializable{
>>>>>>> 8f511e1e81669a2aeeb29a6aea8f6491c2335deb

	private static final long serialVersionUID = 1L;
	
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matrimonyId")
=======
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "my_sequence", sequenceName = "dbsequence", initialValue = 10000, allocationSize = 10)
>>>>>>> 8f511e1e81669a2aeeb29a6aea8f6491c2335deb
	private Integer matrimonyId;
	private String phoneNumber;
	private String password;
	private String status;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userMatrimonyId")
	private UserProfile userProfile;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "loginMatrimonyId")
	private Set<UserProfileInterest> userProfileInterest;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "interestMatrimonyId")
	private Set<UserProfileInterest> userProfileMatchInterest;
}
