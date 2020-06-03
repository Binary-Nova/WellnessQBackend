package application.rest.doctor;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Doctor {
	
	@Id
	private String id;
	private String email;
	private String password;
	private String name;
	private String category;
	private String lattitude;
	private String longitude;
	private String timeslot;
	private long queueToken;
	private String queueCapacity;
	private long phoneNumber;
	
}