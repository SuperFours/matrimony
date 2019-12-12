package com.matrimony.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description - common response params like status, statusCode and message.
 * @author Govindasamy.C
 * @since
 */
@Setter
@Getter
public class ResponseDto {

	private String status;
	private String message;
	private Integer statusCode;
}
