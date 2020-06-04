package application.rest.doctor;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor,Long> {
	Doctor findByPhoneNumber(long phoneNumber);
	Doctor findById(String id);
	List<Doctor> findAllDoctorsByCategory(String category);
}