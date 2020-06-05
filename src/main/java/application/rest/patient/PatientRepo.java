package application.rest.patient;


import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface PatientRepo extends CrudRepository<Patient,String> {
	
	Patient findByPhoneNumber(String patientPhoneNumber);
	List<Patient> findBySlot(String timeslot);
 
}
