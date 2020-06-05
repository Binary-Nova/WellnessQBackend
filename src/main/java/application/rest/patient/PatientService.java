package application.rest.patient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.rest.Exception.InvalidPasswordException;

import application.rest.doctor.Doctor;
import application.rest.doctor.DoctorRepo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PatientService {

	@Autowired
	PatientRepo repo;
	DoctorRepo prepo;

	public void savePatient(Patient c) {
		// TODO Auto-generated method stub
		repo.save(c);

	}

	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub

		return (List<Patient>) repo.findAll();
	}

	public String getPatient(String name, String number) {
		// TODO Auto-generated method stub
		Patient c = repo.findByPhoneNumber(number);
		if (c == null) {
			Patient p = new Patient();
			p.setName(name);
			p.setPhoneNumber(number);
			repo.save(p);
			return "created";
		} else if ((c != null) && (name.equalsIgnoreCase(c.getName()))) {
			return "success";
		}
		return "failed";
	}

	public List<Doctor> getMyQueue(String patientPhoneNumber) {
		
		// TODO Auto-generated method stub
		Patient p = repo.findByPhoneNumber(patientPhoneNumber);
		List<String> docphnumList = p.getDoctorsPhoneNumber();
		List<Doctor> visitDoc = new ArrayList<Doctor>();
		for (int i = 0; i < docphnumList.size(); i++) {
			String docPhNum = docphnumList.get(i);
			Doctor d = prepo.findByPhoneNumber(docPhNum);
			visitDoc.add(d);
		}
		return visitDoc;
	}

	public Patient editPatient(Patient p) {
		// TODO Auto-generated method stub
		return repo.save(p);
	}

	public String removeDoctorFromList(String patientPhoneNumber, String doctorPhoneNumber) {
		// TODO Auto-generated method stub
		Patient p = repo.findByPhoneNumber(patientPhoneNumber);
		List<String> docphnumList = p.getDoctorsPhoneNumber();
		if (docphnumList.remove(doctorPhoneNumber))
			return "deleted";
		else
			return "failed";
	}

	public int getTokenNum(String slot, String patientPhoneNumber) {
		// TODO Auto-generated method stub
		Patient p=repo.findByPhoneNumber(patientPhoneNumber);
		int q=0;
		if(p.getSlot().equalsIgnoreCase(slot))
		{
			q=p.getQueueToken();
		}
		return q;
	}

	public int getMyToken(String phone) {
		// TODO Auto-generated method stub
		Patient patient= repo.findByPhoneNumber(phone);
		return patient.getQueueToken();
	}


}
