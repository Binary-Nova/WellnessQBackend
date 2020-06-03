package application.rest.department;



import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;



@Data
@Document
public class Category {
	private String name;
	@Id
	private String id;
	private String desc;
	private String longitude;
	private String latitude;
}
