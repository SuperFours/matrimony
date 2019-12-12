package com.matrimony.service;

import com.matrimony.dto.InterestRequestDto;
import com.matrimony.dto.RegisterRequestDto;
import com.matrimony.dto.RegisterResponseDto;
import com.matrimony.dto.ResponseDto;

import javassist.NotFoundException;

public interface UserService {

	public RegisterResponseDto userRegistration(RegisterRequestDto registerRequestDto);

	public ResponseDto sendInterest(Integer matrimonyId, InterestRequestDto interestRequestDto)
			throws NotFoundException;

}
