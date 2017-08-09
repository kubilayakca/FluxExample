package com.webflux.demo;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
	private long id;
	//private String userForeName;
	//private String userSurname;	
	private Date when;	
}
