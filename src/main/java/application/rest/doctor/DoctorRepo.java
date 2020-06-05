package application.rest.doctor;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor,String> {
	Doctor findByPhoneNumber(String phoneNumber);
	//Doctor findById(String id);
	List<Doctor> findAllDoctorsByCategory(String category);
}