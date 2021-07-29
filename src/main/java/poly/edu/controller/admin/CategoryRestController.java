package poly.edu.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;
import poly.edu.repository.CategoryRepository;
import poly.edu.service.CategoryService;

@RequestMapping("admin/categories")
@RestController
public class CategoryRestController {
	
	@Autowired
	CategoryRepository repository;
	
	@Autowired
	CategoryService service;
	
	@GetMapping("getAllByPage")
	public  ResponseEntity<?> getAllByPage(PageModel pageModel){
		List<CategoryModel> list = service.getAllByPage(pageModel);
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("getAll")
	public  ResponseEntity<?> getAll(){
		List<CategoryModel> list = service.getAll();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "createOrUpdate")
	public ResponseEntity<?> createOrUpdate(
			@RequestBody CategoryModel categoryModel
		) {
		if(categoryModel.getId()!=null) {
			return ResponseEntity.ok(service.update(categoryModel));
		}
		return ResponseEntity.ok(service.create(categoryModel));
	}
	
}
