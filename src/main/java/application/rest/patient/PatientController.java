package application.rest.patient;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.rest.doctor.Doctor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
public class PatientController {

	@Autowired
	PatientService patientservice;

	@PostMapping("/savepatient")
	public void savePatient(@RequestBody Patient c) {
		patientservice.savePatient(c);
	}
	
	@PutMapping("/editPatient")
	public Patient editPatient(@RequestBody Patient p) {
		return patientservice.editPatient(p);
	}

	@GetMapping("/getallpatient")
	public List<Patient> getAllPatient()
	// TODO change return type
	{
		return patientservice.getAllPatient();

	}
	
//	@PostMapping("/getmyqueued")
//	public List<Doctor> getMyQueue(@RequestBody Patient c){
//		return patientservice.getMyQueue(c.getId());
//	}
	


	@PostMapping("/loginPatient")
	public String loginCon(@RequestBody Patient c) {
		log.info("Request to login API... \n ph number" + c.getPhoneNumber() + " \n name" + c.getName());
		String st = patientservice.getPatient(c.getName(), c.getPhoneNumber());
		return st;

	}
}
