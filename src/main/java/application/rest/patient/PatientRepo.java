package application.rest.patient;


import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PatientRepo extends CrudRepository<Patient,String> {
	
	 @Query("{phoneNumber : ?0}")
	Patient findByPhoneNumber(String patientPhoneNumber);
	 
	 @Query("{slot : ?0}")
	List<Patient> findBySlot(String slot);
 
}
