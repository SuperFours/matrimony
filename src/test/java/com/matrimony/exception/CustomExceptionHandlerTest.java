package com.matrimony.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matrimony.constant.AppConstant;
import com.matrimony.dto.ResponseDto;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomExceptionHandlerTest {

	@InjectMocks
	CustomExceptionHandler customExceptionHandler;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testHandleNoRecordFoundException() {
		NotFoundException notFoundException = new NotFoundException("No Profiles Found.");
		ResponseEntity<ResponseDto> response = customExceptionHandler
				.handleNoRecordFoundException(notFoundException);
		assertEquals(AppConstant.FAILURE, response.getBody().getStatus());
	}
}
