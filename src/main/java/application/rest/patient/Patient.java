package application.rest.patient;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import application.rest.department.Category;
import application.rest.doctor.Doctor;
import lombok.Data;

@Data
@Document

public class Patient {
	
	private String name;
	@Id
	private String phoneNumber;
	private String lattitude;
	private String longitude;
	private int queueToken;
	private List<String> doctorsPhoneNumber;
	private String slot;
}
