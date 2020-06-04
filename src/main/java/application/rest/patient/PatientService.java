package application.rest.patient;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.rest.Exception.InvalidPasswordException;

import application.rest.doctor.Doctor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PatientService {

	@Autowired
	PatientRepo repo;

	public void savePatient(Patient c) {
		// TODO Auto-generated method stub
		repo.save(c);

	}

	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub

		return (List<Patient>) repo.findAll();
	}

	public String getPatient(String name, long number) {
		// TODO Auto-generated method stub
		Patient c = repo.findByPhoneNumber(number);
		if(c==null) {
			Patient p = new Patient();
			p.setName(name);
			p.setPhoneNumber(number);
			repo.save(p);
			return "created";
		}
		else if ((c != null) && (name.equalsIgnoreCase(c.getName()))) {
				return "success";			
			}
		return "failed";
	}

//	public List<Doctor> getMyQueue(String id) {
//		// TODO Auto-generated method stub
//		return repo.findDoctorByid(id);
//	}

	public Patient editPatient(Patient p) {
		// TODO Auto-generated method stub
		return repo.save(p);
	}

//	public void addMyqueue(Category b) {
//		// TODO Auto-generated method stub
//		
//		
//	}

}
