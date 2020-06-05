package application.rest.doctor;



import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor,String> {
	
	 @Query("{phoneNumber : ?0}")
	Doctor findByPhoneNumber(String phoneNumber);

	 @Query("{category : ?0}")
	List<Doctor> findAllDoctorsByCategory(String category);
}