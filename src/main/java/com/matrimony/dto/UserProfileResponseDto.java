package com.matrimony.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserProfileResponseDto {
	
	private String message;
	private Integer statusCode;
	
	List<UserProfileRequestDto> userProfiles;

}
