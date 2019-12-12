package com.matrimony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.dto.LoginDto;
import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
import com.matrimony.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceImplTest {
	
	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	UserRepository userRepository;
	
	User user = new User();
	UserProfile userProfile = new UserProfile();

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		user.setMatrimonyId(111222);
		user.setPhoneNumber("8675958381");
		
		userProfile.setName("Moorthy");
		user.setUserProfile(userProfile);
	}

	
	@Test
	public void testLogin() {
		LoginDto loginDto = new LoginDto();
		loginDto.setPhoneNumber("8675958381");
		loginDto.setPassword("start@123");
		
		when(userRepository.findByPhoneNumberAndPassword(loginDto.getPhoneNumber(), loginDto.getPassword())).thenReturn(user);
		User response = loginServiceImpl.login(loginDto);
		assertEquals("8675958381", response.getPhoneNumber());
	}

}
