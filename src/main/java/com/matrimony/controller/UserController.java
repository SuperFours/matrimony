package com.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.service.UserService;

/**
 * @description - User Controller can handles the all the user functionalities
 *              such as user profile creation
 * @author Govindasamy.C
 * @since 12-12-2019
 */
@RestController
@RequestMapping("/users/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	
}
