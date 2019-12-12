package com.matrimony.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {

	@NotBlank(message = "phoneNumber should be mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid phoneNumber.")
	private String phoneNumber;

	@NotBlank(message = "password should be mandatory")
	private String password;
}
