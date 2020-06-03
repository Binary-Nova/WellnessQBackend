package application.rest.department;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryservice;
	
	@PostMapping("/savecategory")
	public void saveCategory(@RequestBody Category b)
	{
		log.info("saving category..."+b.getName());
		categoryservice.saveCategory(b);
	}
	
	
	@PutMapping("/updatecategory")
	public void updateCategory(@RequestBody Category b)
	{
		log.info("updating category..."+b.getName());
		categoryservice.updateCategory(b);
	}
	
	@DeleteMapping("/deletecategory")
	public void deleteCategory(@RequestBody Category b)
	{
		log.info("deleting Category..."+b.getName());
		categoryservice.deleteCategory(b);
	}
	
	
	@GetMapping("/getallbusiness")
	public List<Category> getAllCategory()
	//TODO change return type
	{ 
		return categoryservice.getAllCategory();
		
	}
	
	/*
	 * @PutMapping("/queueplus") public Category incrementQueue(@PathVariable String
	 * email,@RequestBody Category b) { return
	 * categoryservice.queueIncreamentCategory(email, b); }
	 * 
	 * @PutMapping("/queueminus") public Category decrementQueue(@RequestBody
	 * Category b) { return categoryservice.queueDecreamentCategory(b); }
	 */

}
