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

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Component
@Slf4j
public class DoctorService {

	@Autowired
	DoctorRepo prepo;
	private int i;

	public void saveDoctor(Doctor p) {
		prepo.save(p);
	}

	public List<Doctor> getAllDoctor() {
		return (List<Doctor>) prepo.findAll();
	}

	public String getDoctor(String name, long number) {
		// TODO Auto-generated method stub
		Doctor c = prepo.findByPhoneNumber(number);
		if(c==null) {
			Doctor doctor = new Doctor();
			doctor.setName(name);
			doctor.setPhoneNumber(number);
			prepo.save(doctor);
			return "created";
		}
		else if ((c != null) && (name.equalsIgnoreCase(c.getName()))) {
			return "success";
		}
		return "failed"; 
	}

	public Doctor queueIncrementDoctor(String slot, Doctor b) {
		// TODO Auto-generated method stub
		Doctor doc = prepo.findById(b.getId());
		List<HashMap<String, Long>> listOfSlotsQ = doc.getTimeslotMap();
		for (int i = 0; i < listOfSlotsQ.size(); i++) {
			Iterator hmIterator = listOfSlotsQ.get(i).entrySet().iterator();
			HashMap<String, Long> qmap = new HashMap<String, Long>();
			while (hmIterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) hmIterator.next();
				if (mapElement.getKey().equals(slot)) {
					long queueToken = ((long) mapElement.getValue() + 1);
					System.out.println(mapElement.getKey() + " : " + queueToken);
					mapElement.setValue(queueToken);
				} // end if
				qmap.put(mapElement.getKey().toString(), (long) mapElement.getValue());
			} // end while
			listOfSlotsQ.set(i, qmap);
		}
		return prepo.save(doc);
	}

	public Doctor queueDecrementDoctor(String slot, Doctor b) {
		// TODO Auto-generated method stub
		Doctor doc = prepo.findById(b.getId());
		List<HashMap<String, Long>> listOfSlotsQ = doc.getTimeslotMap();
		for (int i = 0; i < listOfSlotsQ.size(); i++) {
			Iterator<Entry<String, Long>> hmIterator = listOfSlotsQ.get(i).entrySet().iterator();
			HashMap<String, Long> qmap = new HashMap<String, Long>();
			while (hmIterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) hmIterator.next();
				if (mapElement.getKey().equals(slot)) {
					long queueToken = ((long) mapElement.getValue() - 1);
					System.out.println(mapElement.getKey() + " : " + queueToken);
					mapElement.setValue(queueToken);
				} // end if
				qmap.put(mapElement.getKey().toString(), (long) mapElement.getValue());
			} // end while
			listOfSlotsQ.set(i, qmap);
		} // end for
		return prepo.save(doc);
	}

	public Doctor editDoctor(Doctor d) {
		// TODO Auto-generated method stub
		return prepo.save(d);

	}

	public List<Doctor> getAvailableDoctor(String category, String slot) {
	//public List<Doctor> getAvailableDoctor(JSONObject json) {
		// TODO Auto-generated method stub
//		String category = json.get("category").toString();
//		String slot = json.get("timeslot").toString();
		List<Doctor> doctorlist = prepo.findAllDoctorsByCategory(category);
		List<Doctor> availableDoctors = new ArrayList<Doctor>();
		for (int i = 0; i < doctorlist.size(); i++)// list doctors according to category
		{
			Doctor doc = doctorlist.get(i);
			List<HashMap<String, Long>> listOfSlotsQ = doc.getTimeslotMap();
			long queueCapacity = doc.getQueueCapacity();
			boolean flag = false;
			for (int j = 0; j < listOfSlotsQ.size(); j++)// slotQ list
			{
				Iterator<Entry<String, Long>> hmIterator = listOfSlotsQ.get(i).entrySet().iterator();
				while (hmIterator.hasNext()) {
					Map.Entry mapElement = (Map.Entry) hmIterator.next();
					if (mapElement.getKey().equals(slot)) {
						long queue = (queueCapacity - (long) mapElement.getValue());
						System.out.println(mapElement.getKey() + " : " + queue);
						if (queue != 0)// check queue is full or not
							flag = true;
					} // end if
				} // end while
			} // end for
			if (flag == true) {
				availableDoctors.add(doc);
			} // endif
		} // end for

		return availableDoctors;
	}

}
