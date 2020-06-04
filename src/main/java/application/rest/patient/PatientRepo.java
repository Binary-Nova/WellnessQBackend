package application.rest.patient;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.rest.doctor.Doctor;

public interface PatientRepo extends CrudRepository<Patient,Long> {
	
	Patient findByPhoneNumber(long phoneNumber);
//	List<Doctor> findDoctorByid(String id);
 
}
