package com.matrimony.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginDto {

	@NotBlank(message = "userId should be mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile no.")
	private String userId;

	@NotBlank(message = "password should be mandatory")
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
