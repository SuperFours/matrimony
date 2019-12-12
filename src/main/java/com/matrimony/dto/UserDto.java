package com.matrimony.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author akuthota.raghu
 * @since 12-12-2019
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Integer userMatrimonyId;
	private String name;
	private Integer age;
	private String city;
	private String educationDetail;
	private String imageUrl;
	private String status;
}
