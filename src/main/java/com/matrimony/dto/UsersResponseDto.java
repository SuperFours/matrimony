package com.matrimony.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto {

	private String message;
	private Integer statusCode;
	
	private List<UserDto> interestedProfiles;
}
