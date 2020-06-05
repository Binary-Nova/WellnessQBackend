package application.rest.patient;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/getmyqueued/{patientPhoneNumber}")
	public List<Doctor> getMyQueue(@PathVariable String patientPhoneNumber){
		return patientservice.getMyQueue(patientPhoneNumber);
	}
	
	//queuetoken only int return 
	//req:slot and phone number
	@GetMapping("/getToken/{slot}/{patientPhoneNumber}")
	public int getToken(@PathVariable String slot, @PathVariable String patientPhoneNumber)
	{
		return patientservice.getTokenNum(slot,patientPhoneNumber);
	}
	
		
	@DeleteMapping("/deleteFromMyQueue/{patientPhoneNumber}/{doctorPhoneNumber}")
	public String deleteDoctorfromMyqueue(@PathVariable String patientPhoneNumber,@PathVariable String doctorPhoneNumber)
	//remove from my queue delete doctor number from the list//
	{
		return patientservice.removeDoctorFromList(patientPhoneNumber,doctorPhoneNumber);
		
	}
	
	@PostMapping("/loginPatient")
	public String loginCon(@RequestBody Patient c) {
		log.info("Request to login API... \n ph number" + c.getPhoneNumber() + " \n name" + c.getName());
		String st = patientservice.getPatient(c.getName(), c.getPhoneNumber());
		return st;

	}
}
