package application.rest.doctor;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Slf4j

@RestController
public class MedicalController {

	@Autowired
	DoctorService doctorservice;

	@PostMapping("/loginDoctor")
	public String loginDoctor(@RequestBody Doctor m) {
		//Response res = new Response();
		String st = doctorservice.getDoctor(m.getName(), m.getPhoneNumber());
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

	@PutMapping("/queueplusdoctor/{slot}/{patientPhoneNumber}")
	public int incrementQueue(@PathVariable String slot,@PathVariable String patientPhoneNumber, @RequestBody Doctor b) 
	{
		log.info("Token + request");
		return doctorservice.queueIncrementDoctor(slot,patientPhoneNumber,b);
	}
	
	@PutMapping("/queueminusdoctor/{slot}")
	public int decrementQueue(@PathVariable String slot, @RequestBody Doctor b) 
	{
		log.info("Token - request");
		return doctorservice.queueDecrementDoctor(slot,b);
	}
	
	//@PostMapping("/getavailabledoctors")
	@RequestMapping(path = "/getavailabledoctors/{category}/{slot}", method = RequestMethod.GET)
	public List<Doctor> getAvailableDoctors(@PathVariable String category, @PathVariable String slot) {
		return doctorservice.getAvailableDoctor(category , slot);
	}
}
