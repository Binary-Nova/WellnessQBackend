package application.rest.department;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.rest.patient.Patient;
import application.rest.patient.PatientRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CategoryService {

	@Autowired
	CategoryRepo repo;
	PatientRepo crepo;
	
	public Category saveCategory(Category b) {
		// TODO Auto-generated method stub
		return repo.save(b);

	}

	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return (List<Category>) repo.findAll();
	}

	public Category updateCategory(Category b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}
	
	public String deleteCategory(Category b) {
			repo.delete(b);
		if(repo.findById(b.getId())!=null)
			return "delete failed";
		else
			return "deleted";
		
		
	}

	/*
	 * public Category queueIncreamentCategory(String email, Category b) { // TODO
	 * Auto-generated method stub Category bus=repo.findById(b.getId()).get(); int
	 * q=bus.getQueueToken(); bus.setQueueToken(++q);
	 * log.info("queue token increased to ..."+bus.getQueueToken()); Patient
	 * con=crepo.findByEmail(email); List<Category>
	 * doctor_list=con.getDoctor_list(); doctor_list.add(b);
	 * con.setDoctor_list(doctor_list); crepo.save(con); return repo.save(bus); }
	 * 
	 * public Category queueDecreamentCategory(Category b) { // TODO Auto-generated
	 * method stub Category bus=repo.findById(b.getId()).get(); int
	 * q=bus.getQueueToken(); bus.setQueueToken(--q);
	 * log.info("queue token decreased to ..."+bus.getQueueToken()); return
	 * repo.save(bus); }
	 */
	
}
