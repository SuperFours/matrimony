package com.matrimony.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {
	
	private String message;
	private Integer statusCode;
	
	ProfileDto profile;

}
