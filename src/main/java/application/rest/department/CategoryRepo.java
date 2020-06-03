package application.rest.department;



import org.springframework.data.repository.CrudRepository;



public interface CategoryRepo extends CrudRepository<Category,String> {
	//Business findById(String id);
	
}
