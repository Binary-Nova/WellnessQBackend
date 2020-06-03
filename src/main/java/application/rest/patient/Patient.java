package application.rest.patient;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import application.rest.department.Category;
import lombok.Data;

@Data
@Document

public class Patient {
	@Id
	private String id;
	private String email;
	private String password;
	private String name;
	private long phoneNumber;
	private String latitude;
	private String longitude;
	private long queueToken;
	@Indexed 
    private List<Category> doctor_list;
}
