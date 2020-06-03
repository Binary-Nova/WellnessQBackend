package application.rest.patient;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.rest.doctor.Doctor;

public interface PatientRepo extends CrudRepository<Patient,String> {
	
	Patient findByEmail(String email);
	List<Doctor> findDoctorByid(String id);
 
}
