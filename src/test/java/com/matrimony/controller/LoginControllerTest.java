package com.matrimony.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrimony.constant.AppConstant;
import com.matrimony.dto.LoginDto;
import com.matrimony.dto.LoginResponseDto;
import com.matrimony.entity.User;
import com.matrimony.entity.UserProfile;
import com.matrimony.exception.CustomExceptionHandler;
import com.matrimony.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
	LoginService loginService;
	User user = new User();
	UserProfile userProfile = new UserProfile();

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

		user.setMatrimonyId(111222);
		
		userProfile.setName("Moorthy");
		user.setUserProfile(userProfile);
	}

	@Test
	public void testLogin() {
		LoginDto userDto = new LoginDto();
		userDto.setPhoneNumber("8675958381");
		userDto.setPassword("start@123");
		when(loginService.login(userDto)).thenReturn(user);

		ResponseEntity<LoginResponseDto> response = loginController.login(userDto);
		assertEquals(AppConstant.SUCCESS, response.getBody().getStatus());
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testInvalidLogin() {
		LoginDto userDto = new LoginDto();
		userDto.setPhoneNumber("8675958381");
		userDto.setPassword("start");
		when(loginService.login(userDto)).thenReturn(null);
		ResponseEntity<LoginResponseDto> response = loginController.login(userDto);
		assertEquals(AppConstant.FAILURE, response.getBody().getStatus());
	}

	@Test
	public void testInvalidData() throws Exception {

		WebRequest webrequest = null;
		LoginDto userDto = new LoginDto();
		userDto.setPhoneNumber("moorthy127");
		userDto.setPassword("start");

		// MvcResult for mockmvc performed
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result).isNotNull();

		new CustomExceptionHandler().handleException(result.getResolvedException(), webrequest);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
