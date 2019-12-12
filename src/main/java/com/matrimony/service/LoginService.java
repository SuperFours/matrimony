package com.matrimony.service;

import com.matrimony.dto.LoginDto;
import com.matrimony.entity.User;

@FunctionalInterface
public interface LoginService {

	User login(LoginDto loginDto);
}
