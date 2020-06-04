package application.rest.doctor;


import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Doctor {
	@Id
	private String id;
	private String name;
	private String category;
	private String lattitude;
	private String longitude;
	private List<HashMap<String,Long>> timeslotMap;
	private long queueCapacity;
	private long phoneNumber;
	
}