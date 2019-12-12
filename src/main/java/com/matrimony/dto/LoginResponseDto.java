package com.matrimony.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto extends ResponseDto{

	private Integer matrimonyId;
	private String userName;
}
