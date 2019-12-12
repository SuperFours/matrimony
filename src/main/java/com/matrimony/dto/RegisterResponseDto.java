package com.matrimony.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description RegisterResponseDto we can pass the response params for the user
 *              profile creation for matrimony application.
 * @author Govindasamy.C
 * @since 12-12-2019
 *
 */
@Setter
@Getter
public class RegisterResponseDto {

	private String message;
	private Integer statusCode;
	private Integer matrimonyId;
}
