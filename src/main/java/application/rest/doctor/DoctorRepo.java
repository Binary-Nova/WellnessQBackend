package application.rest.doctor;



import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor,Long> {
	Doctor findByEmail(String email);
	Doctor findById(String id);
}