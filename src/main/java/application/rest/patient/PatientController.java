package application.rest.patient;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.rest.doctor.Doctor;
import lombok.extern.java.Log;
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

	@GetMapping("/getallpatient")
	public List<Patient> getAllPatient()
	// TODO change return type
	{
		return patientservice.getAllPatient();

	}
	
	@PostMapping("/getmyqueued")
	public List<Doctor> getMyQueue(@RequestBody Patient c){
		return patientservice.getMyQueue(c.getId());
	}
	


	@PostMapping("/loginPatient")
	public String loginCon(@RequestBody Patient c) {
		log.info("Request to login API" + c.getEmail() + " " + c.getPassword());
		String st = patientservice.getPatient(c.getEmail(), c.getPassword());
		return st;

	}
}
