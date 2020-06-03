package application.rest.doctor;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
public class MedicalController {

	@Autowired
	DoctorService doctorservice;

	@PostMapping("/loginDoctor")
	public String loginDoctor(@RequestBody Doctor m) {
		//Response res = new Response();
		String st = doctorservice.getDoctor(m.getEmail(), m.getPassword());
		log.info("DB Response" + st);

		return st;

	}
	
	@PutMapping("/editDoctor")
	public Doctor editDoctor(@RequestBody Doctor d) {
		log.info("editing doctor/medical center");
		return doctorservice.editDoctor(d);
		 
	}
	
	@GetMapping("/getAllDoctors")
	public List<Doctor> getAllDoctors(){
		log.info("getting all doctors....");
		return doctorservice.getAllDoctor();
	}

	@PutMapping("/queueplusdoctor")
	public Doctor incrementQueue(@RequestBody Doctor b) 
	{
		return doctorservice.queueIncrementDoctor( b);
	}
	
	@PutMapping("/queueminusdoctor")
	public Doctor decrementQueue(@RequestBody Doctor b) 
	{
		return doctorservice.queueDecrementDoctor(b);
	}


}
