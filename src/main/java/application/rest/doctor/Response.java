package application.rest.doctor;


import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response {
	
	private HttpStatus status;
	private String message;
	private String result;
	

}
