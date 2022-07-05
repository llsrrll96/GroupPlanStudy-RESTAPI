package com.springboot.gpsapi.payload;

import lombok.Data;

@Data
public class APIMessage 
{
	private String message;
	private Object data;
	
	
	public APIMessage( ) {
		super();
		this.message = message;
		this.data = data;
	}
	
}
