package application.rest.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.rest.Exception.InvalidPasswordException;
import application.rest.patient.Patient;
import application.rest.patient.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Component
@Slf4j
public class DoctorService {

	@Autowired
	DoctorRepo prepo;
	
	@Autowired
	PatientRepo repo;

	public Doctor saveDoctor(Doctor p) {
		return prepo.save(p);
	}

	public List<Doctor> getAllDoctor() {
		return (List<Doctor>) prepo.findAll();
	}

	public String getDoctor(String name, String number) {
		// TODO Auto-generated method stub
		Doctor c = prepo.findByPhoneNumber(number);
		if (c == null) {
			Doctor doctor = new Doctor();
			doctor.setName(name);
			doctor.setPhoneNumber(number);
			prepo.save(doctor);
			return "created";
		} else if ((c != null) && (name.equalsIgnoreCase(c.getName()))) {
			return "success";
		}
		return "failed";
	}

	
	public Doctor editDoctor(Doctor d) {
		// TODO Auto-generated method stub
		return prepo.save(d);

	}

	public List<Doctor> getAvailableDoctor(String category, String slot) {
		List<Doctor> doctorlist = prepo.findAllDoctorsByCategory(category);
		List<Doctor> availableDoc = new ArrayList<Doctor>();

		for (Doctor d : doctorlist) {
			HashMap<String, Integer> timeslots = d.getTimeslotMap();

			if (timeslots.containsKey(slot)) {
				int queuevalue = timeslots.get(slot);
				if (d.getQueueCapacity() - queuevalue > 0)
					availableDoc.add(d);

			}

		}

		return availableDoc;
	}

	public int queueIncrementDoctor(String slot, String patientPhoneNumber, Doctor doc) {
		// TODO Auto-generated method stub
		Doctor d=prepo.findByPhoneNumber(doc.getPhoneNumber());
		HashMap<String,Integer> hm= d.getTimeslotMap();
		int current_queue=hm.get(slot);
		hm.remove(slot);
		hm.put(slot, ++current_queue);
		
		d.setTimeslotMap(hm);
		prepo.save(d);
		
		Patient p= new Patient();
	    p=repo.findByPhoneNumber(patientPhoneNumber);
	    p.setSlot(slot);
		p.setQueueToken(current_queue);
		repo.save(p);
		
		return current_queue;
	}

	public int queueDecrementDoctor(String slot, Doctor doc) {
		// TODO Auto-generated method stub
		
		Doctor d=prepo.findByPhoneNumber(doc.getPhoneNumber());
		HashMap<String,Integer> hm= d.getTimeslotMap();
		int current_queue=hm.get(slot);
		hm.remove(slot);
		hm.put(slot, --current_queue);
		d.setTimeslotMap(hm);
		prepo.save(d);
		
		List<Patient> patients= repo.findBySlot(slot);
		for(Patient p:patients)
		{
			int queue= p.getQueueToken();
			p.setQueueToken(--queue);
			repo.save(p);
		}
		
		return current_queue;
	}
}
