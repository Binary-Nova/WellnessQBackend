package application.rest.doctor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.rest.Exception.InvalidPasswordException;
import application.rest.department.Category;
import application.rest.patient.Patient;
import application.rest.patient.PatientService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DoctorService {
	
	@Autowired
	DoctorRepo prepo;
	
	public void saveDoctor(Doctor p) {
		prepo.save(p);
	}

	public List<Doctor> getAllDoctor() {
		return (List<Doctor>) prepo.findAll();
	}
	
	public String getDoctor(String email, String password) {
		// TODO Auto-generated method stub
		Doctor c = prepo.findByEmail(email);
		if (c != null) {
			if (c.getPassword().equals(password))
				return "success";			
			else
				throw new InvalidPasswordException("Password Invalid");
		} else
		{
			Doctor doctor = new Doctor();
			doctor.setEmail(email);
			doctor.setPassword(password);
			prepo.save(doctor);
			return "created";
		}

	}

	public Doctor queueIncrementDoctor( Doctor b) {
		// TODO Auto-generated method stub
		Doctor doc=prepo.findById(b.getId());
		long q= doc.getQueueToken();
		doc.setQueueToken(++q);
		log.info("queue token increased to ..."+doc.getQueueToken());
		return prepo.save(doc);
	}
	
	public Doctor queueDecrementDoctor(Doctor b) {
		// TODO Auto-generated method stub
		Doctor doc=prepo.findById(b.getId());
		long q=doc.getQueueToken();
		doc.setQueueToken(--q);
		log.info("queue token decreased to ..."+doc.getQueueToken());
		return prepo.save(doc);
	}

}
